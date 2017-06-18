package org.grandtestauto.indoflash;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

//import com.google.analytics.tracking.android.EasyTracker;

public class WordListDisplay extends Activity {

    private IndoFlash application;
    private TextView chapterTitle;
    private TextView wordView;
    private TextView definitionView;
    private Button nextButton;
    private ImageButton indonesianFirstButton;
    private ImageButton addRemoveFavouriteButton;
    private ImageButton shuffleButton;
    private boolean showDefinition;
    private boolean finished = false;
    private int currentPosition = 0;
    private WordList wordList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);
        chapterTitle = (TextView) findViewById(R.id.word_list_title_view);
        wordView = (TextView) findViewById(R.id.word_view);
        definitionView = (TextView) findViewById(R.id.definition_view);
        indonesianFirstButton = (ImageButton) findViewById(R.id.indonesian_first_button);
        indonesianFirstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleIndonesianFirst();
            }
        });
        nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setText(R.string.show);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                next();
            }
        });
        shuffleButton = (ImageButton) findViewById(R.id.shuffle_button);
        shuffleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleShuffle();
            }
        });

        ImageButton showListsButton = (ImageButton) findViewById(R.id.show_word_lists_button);
        showListsButton.setImageResource(R.drawable.ic_lists);
        showListsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(WordListDisplay.this, WordListSelecter.class);
                startActivity(intent);
            }
        });

        addRemoveFavouriteButton = (ImageButton) findViewById(R.id.add_to_favourites_button);
        addRemoveFavouriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addRemoveFavourite();
            }
        });
        application = (IndoFlash) getApplication();
        doSetup();
    }

    private void loadWordList() {
        wordList = application.wordList();
        if (application.shuffle()) {
            List<Word> toShuffle = new LinkedList<>();
            toShuffle.addAll(wordList.words());
            Collections.shuffle(toShuffle);
            wordList = new WordList(toShuffle);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        doSetup();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_info:
                showFeedback();
                return true;
            case R.id.action_help:
                showHelp();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showFeedback() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Add the buttons
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
            }
        });
        builder.setTitle(getApplicationContext().getResources().getString(R.string.info));
        builder.setMessage(getApplicationContext().getResources().getString(R.string.acknowledgements));
        // Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showHelp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Add the buttons
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
            }
        });
        builder.setTitle(getApplicationContext().getResources().getString(R.string.help));
        builder.setMessage(getApplicationContext().getResources().getString(R.string.help_text));
        // Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void toggleIndonesianFirst() {
        application.toggleShowIndonesianFirst();
        showDefinition = false;
        showFirstWord();
        setupIndonesianFirstButton(true);
    }

    private void setupIndonesianFirstButton(boolean showToast) {
        int resourceId = R.string.indonesian_first;
        if (application.showIndonesianFirst()) {
            indonesianFirstButton.setImageResource(R.drawable.ic_down_arrow);
        } else {
            resourceId = R.string.english_first;
            indonesianFirstButton.setImageResource(R.drawable.ic_up_arrow);
        }
        CharSequence description = indonesianFirstButton.getContext().getResources().getText(resourceId);
        indonesianFirstButton.setContentDescription(description);
        if (showToast)
            Toast.makeText(getApplicationContext(), description, Toast.LENGTH_SHORT).show();
    }

    private void toggleShuffle() {
        application.toggleShuffle();
        loadWordList();
        showFirstWord();
        setupShuffleButton();
        int msgId = application.shuffle() ? R.string.shuffle_on_toast : R.string.shuffle_off_toast;
        Toast.makeText(getApplicationContext(), msgId, Toast.LENGTH_SHORT).show();
    }

    private void setupShuffleButton() {
        int resourceId = R.string.unshuffle;
        if (application.shuffle()) {
            shuffleButton.setImageResource(R.drawable.ic_shuffle);
        } else {
            shuffleButton.setImageResource(R.drawable.ic_unshuffle);
            resourceId = R.string.shuffle;
        }
        CharSequence description = shuffleButton.getContext().getResources().getText(resourceId);
        shuffleButton.setContentDescription(description);
    }

    private void doSetup() {
        chapterTitle.setText(application.currentWordList().title());
        setupIndonesianFirstButton(false);
        setupShuffleButton();
        setupFavouritesButton();
        loadWordList();
        showFirstWord();
    }

    private void showFirstWord() {
        if (application.showingFavourites() && wordList.words().isEmpty()) {
            showForEmptyFavourites();
        } else {
            wordView.setText(getWord(0).word());
            definitionView.setText("");
            showDefinition = true;
            currentPosition = 0;
            nextButton.setClickable(true);
            addRemoveFavouriteButton.setClickable(true);
        }
    }

    private void showForEmptyFavourites() {
        finished = true;
        wordView.setText(R.string.favourites_is_empty);
        nextButton.setText("");
        nextButton.setClickable(false);
        addRemoveFavouriteButton.setClickable(false);
    }

    private void setupFavouritesButton() {
        int resourceId = R.string.add_to_favourites;
        if (application.showingFavourites()) {
            resourceId = R.string.remove_from_favourites;
            addRemoveFavouriteButton.setImageResource(R.drawable.ic_remove_from_favourites);
        } else {
            addRemoveFavouriteButton.setImageResource(R.drawable.ic_add_to_favourites);
        }
        CharSequence description = addRemoveFavouriteButton.getContext().getResources().getText(resourceId);
        addRemoveFavouriteButton.setContentDescription(description);
    }

    private void addRemoveFavourite() {
        application.addRemoveFavourite(getWord(currentPosition));
        if (application.showingFavourites()) {
            Toast.makeText(getApplicationContext(), R.string.removed_from_favourites_toast, Toast.LENGTH_SHORT).show();
            if (showDefinition) {
                //Removing a word is like activating Show but we don't want to see the definition of the word just removed.
                showDefinition = false;
            }
            //The user may now be at the end of the list.
            if (currentPosition == wordList.words().size() - 1) {
                finished = true;
                if (application.storedFavourites().words().isEmpty()) {
                    showForEmptyFavourites();
                } else {
                    nextButton.setText(R.string.repeat);
                }
            } else {
                next();
            }
        } else {
            Toast.makeText(getApplicationContext(), R.string.added_to_favourites_toast, Toast.LENGTH_SHORT).show();
        }
    }

    private Word getWord(int index) {
        Word word = wordList.words().get(index);
        if (application.showIndonesianFirst()) return word;
        return new Word(word.definition(), word.word());
    }

    private void next() {
        if (finished) {
            currentPosition = 0;
            wordView.setText(getWord(currentPosition).word());
            definitionView.setText("");
            nextButton.setText(R.string.show);
            showDefinition = true;
            finished = false;
            return;
        }
        if (showDefinition) {
            definitionView.setText(getWord(currentPosition).definition());
            nextButton.setText(R.string.next);
            showDefinition = false;
            if (currentPosition == wordList.words().size() - 1) {
                finished = true;
                nextButton.setText(R.string.repeat);
            }
        } else {
            currentPosition++;
            wordView.setText(getWord(currentPosition).word());
            definitionView.setText("");
            nextButton.setText(R.string.show);
            showDefinition = true;
        }
    }
}
