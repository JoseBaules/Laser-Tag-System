/*
 * Overview: Implementation for the Persistence Handler Interface.
 * @custom.methods
 *      getPlayers()
 *      createPlayer()
 * @version 1.0
 * @since 09/09/22
 * */

package com.example.project;

import javafx.collections.ObservableList;

public abstract class PlayerPersistenceInterface
{

        public static ObservableList<Player> getGreenTeamPlayer(int playerId) {
                return null;
        }

        public boolean createRedPlayer(Player player) {
                return false;
        }

        public boolean createGreenPlayer(Player player) {
                return false;
        }

        /*
         * Adds a player's data to database
         * @param player The player object containing data to be stored
         * @return FALSE if data insertion failed
         * @return TRUE if data insertion was successful
         */

}
