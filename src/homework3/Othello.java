package homework3;
/**
 * @file    -Othello.java
 * @author  -J. Bailey
 * @date    - ...
 *
 * \brief This controls the flow of the game, specific to Othello, such as
 * deciding which pieces to flip after a turn.
 *
 */

public class Othello extends GameImplementation {
	public void setPiece(int x, int y, Player player) {
        if (checkValid(x, y, player) == true) {
            Piece piece = new Piece(player.getColour());
            int playerNumber = -1;
            for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
                if (getPlayer(i) == player) {
                    playerNumber = i;
                }
            }
            int playerColour = -1;
            if (getPlayer(playerNumber).getColour().equalsIgnoreCase("Black")) {
                playerColour = BLACK_PIECE;
            } else {
                playerColour = WHITE_PIECE;
            }
            
            getBoard().setPiece(playerColour, x, y);
            
            flipNorth(x, y, player);
            flipSouth(x, y, player);
            flipWest(x, y, player);
            flipEast(x, y, player);
            flipNorthWest(x, y, player);
            flipNorthEast(x, y, player);
            flipSouthWest(x, y, player);
            flipSouthEast(x, y, player);
        }
    }
    
    public void setBoard(int width, int height) {
        super.setBoard(width, height);
        getBoard().setPiece(WHITE_PIECE,3,3); // (3,3) is the position this piece is placed on the board
        getBoard().setPiece(BLACK_PIECE,3,4); // (3,4) is the position this piece is placed on the board
        getBoard().setPiece(BLACK_PIECE,4,3); // (4,3) is the position this piece is placed on the board
        getBoard().setPiece(WHITE_PIECE,4,4); // (4,4) is the position this piece is placed on the board

    }
    
	public int getWinner() {
		int totalPieces[] = new int[NUMBER_OF_PLAYERS];
		
		if (checkWin() == true) {
			for (int i = 0; i < BOARD_WIDTH; i++) {
				for (int j = 0; j < BOARD_WIDTH; j++) {
                    if (getBoard().getBoard()[i][j] == BLACK_PIECE
                        || getBoard().getBoard()[i][j] == WHITE_PIECE) {
                        totalPieces[getBoard().getBoard()[i][j] - 1]++;
                    }else{}
				}
			}
			if (totalPieces[0] > totalPieces[1]) {
				return 1;
			} else if (totalPieces[1] > totalPieces[0]) {
				return 2;
			} else {
				return 3; // draw
			}
		} else {
			return -1;
		}
	}
	
	private int getPlayerColour(Player player) {
		if (player.getColour().equalsIgnoreCase("Black")) {
			return BLACK_PIECE;
		} else {
			return WHITE_PIECE;
		}
	}
    
	public Othello() {
        this.setBoard(BOARD_WIDTH, BOARD_HEIGHT);
        getPlayer(PLAYER_ONE).setColour("Black");
        getPlayer(PLAYER_TWO).setColour("White");
        getPlayer(PLAYER_ONE).setName("Player 1");
        getPlayer(PLAYER_TWO).setName("Player 2");
	} 
	
	public boolean checkTakeableTurn(Player player) {
		boolean takeable = false;
		for (int i = 0; i < BOARD_WIDTH; i++) {
			for (int j = 0; j < BOARD_WIDTH; j++) {
				if (getBoard().getBoard()[i][j] == 0) {
					if (takeable == false) takeable = checkFlip(i, j, player);
				}
			}
		}
		return takeable;
	}
	
    public boolean checkWin(){
		int[][] boardArray = getBoard().getBoard();
		boolean boardFull = true;
		boolean validMoves = false;
        
		for (int i = 0; i < BOARD_WIDTH; i++) {
			for (int j = 0; j < BOARD_HEIGHT; j++) {
				if (boardArray[i][j] == EMPTY_SQUARE) {
					boardFull = false;
				}
			}
		}
		
		if (boardFull == true) {
			return true;
		}
		
		for (int i = 0; i < BOARD_WIDTH; i++) {
            for (int j = 0; j < BOARD_HEIGHT; j++) {
                for (int k = 0; k < NUMBER_OF_PLAYERS; k++) {
                    if(checkFlip(i,j,getPlayer(k)) == true) validMoves = true; // Only check for the player who's current turn it is. Skip turn if no valid move.
                }
            }
        }
		if (validMoves == false) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean checkFlipNorth(int x, int y, Player player) {
		int playerColour = -1;
		int otherPlayerColour = -1;
		for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
			if (getPlayer(i) == player) {
				playerColour = getPlayerColour(getPlayer(i));
			} else {
				otherPlayerColour = getPlayerColour(getPlayer(i));
			}
		}
		
		boolean validity = false;
		boolean partValid = false;
        
		if (y - 1 >= 0) {
			if (getBoard().getBoard()[x][y-1] == otherPlayerColour) {
				partValid = true;
			}
			for (int i = y - 1; i >= 0; i--) {
				if (getBoard().getBoard()[x][i] == EMPTY_SQUARE) {
					partValid = false;
				}
				if (getBoard().getBoard()[x][i] == playerColour && partValid == true) {
					validity = true;
				}
			}
		}
		return validity;
	}
	
	private boolean checkFlipSouth(int x, int y, Player player) {
		int playerColour = -1;
        int otherPlayerColour = -1;
        for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            if (getPlayer(i) == player) {
                playerColour = getPlayerColour(getPlayer(i));
            } else {
                otherPlayerColour = getPlayerColour(getPlayer(i));
            }
        }
        
		boolean validity = false;
		boolean partValid = false;
        
		if (y + 1 < BOARD_HEIGHT) {
			if (getBoard().getBoard()[x][y+1] == otherPlayerColour) {
				partValid = true;
			}
			for (int i = y + 1; i < BOARD_HEIGHT; i++) {
				if (getBoard().getBoard()[x][i] == EMPTY_SQUARE) {
					partValid = false;
				}
				if (getBoard().getBoard()[x][i] == playerColour && partValid == true) {
					validity = true;
				}
			}
		}
		return validity;
	}
	
	private boolean checkFlipWest(int x, int y, Player player) {
        int playerColour = -1;
		int otherPlayerColour = -1;
		for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
			if (getPlayer(i) == player) {
				playerColour = getPlayerColour(getPlayer(i));
			} else {
				otherPlayerColour = getPlayerColour(getPlayer(i));
			}
		}
        
        boolean validity = false;
        boolean partValid = false;
        
        if (x - 1 >= 0) {
            if (getBoard().getBoard()[x-1][y] == otherPlayerColour) {
                partValid = true;
            }
            for (int i = x - 1; i >= 0; i--) {
                if (getBoard().getBoard()[i][y] == EMPTY_SQUARE) {
                    partValid = false;
                }
                if (getBoard().getBoard()[i][y] == playerColour
					&& partValid == true) {
                    validity = true;
                }
            }
        }
        return validity;
	}
	
	private boolean checkFlipEast(int x, int y, Player player) {
		int playerColour = -1;
        int otherPlayerColour = -1;
        for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            if (getPlayer(i) == player) {
                playerColour = getPlayerColour(getPlayer(i));
            } else {
                otherPlayerColour = getPlayerColour(getPlayer(i));
            }
        }
		
		boolean validity = false;
		boolean partValid = false;
        
		if (x + 1 < BOARD_WIDTH) {
			if (getBoard().getBoard()[x+1][y] == otherPlayerColour) {
				partValid = true;
			}
			for (int i = x + 1; i < BOARD_WIDTH; i++) {
				if (getBoard().getBoard()[i][y] == EMPTY_SQUARE) {
					partValid = false;
				}
				if (getBoard().getBoard()[i][y] == playerColour && partValid == true) {
					validity = true;
				}
			}
		}
        return validity;
	}
	
	private boolean checkFlipNorthWest(int x, int y, Player player) {
		int playerColour = -1;
		int otherPlayerColour = -1;
		for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
			if (getPlayer(i) == player) {
				playerColour = getPlayerColour(getPlayer(i));
			} else {
				otherPlayerColour = getPlayerColour(getPlayer(i));
			}
		}
		
		boolean validity = false;
		boolean partValid = false;
        
		if (x - 1 >= 0 && y - 1 >= 0) {
			if (getBoard().getBoard()[x-1][y-1] == otherPlayerColour) {
				partValid = true;
			}
			int i = x - 1;
			int j = y - 1;
			while (i >= 0 && j >= 0) {
				if (getBoard().getBoard()[i][j] == EMPTY_SQUARE) {
					partValid = false;
				}
				if (getBoard().getBoard()[i][j] == playerColour
					&& partValid == true) {
					validity = true;
				}
				i--;
				j--;
			}
		}
		return validity;
	}
	
	private boolean checkFlipNorthEast(int x, int y, Player player) {
		int playerColour = -1;
        int otherPlayerColour = -1;
        for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            if (getPlayer(i) == player) {
                playerColour = getPlayerColour(getPlayer(i));
            } else {
                otherPlayerColour = getPlayerColour(getPlayer(i));
            }
        }
        
		boolean validity = false;
		boolean partValid = false;
        
		if (x + 1 < BOARD_WIDTH && y - 1 >= 0) {
			if (getBoard().getBoard()[x+1][y-1] == otherPlayerColour) {
				partValid = true;
			}
			int i = x + 1;
			int j = y - 1;
			while (i < BOARD_WIDTH && j >= 0) {
				if (getBoard().getBoard()[i][j] == EMPTY_SQUARE) {
					partValid = false;
				}
				if (getBoard().getBoard()[i][j] == playerColour && partValid == true) {
					validity = true;
				}
				i++;
				j--;
			}
		}
		return validity;
	}
	
	private boolean checkFlipSouthEast(int x, int y, Player player) {
		int playerColour = -1;
        int otherPlayerColour = -1;
        for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            if (getPlayer(i) == player) {
                playerColour = getPlayerColour(getPlayer(i));
            } else {
                otherPlayerColour = getPlayerColour(getPlayer(i));
            }
        }
		
		boolean validity = false;
		boolean partValid = false;
        
		if (x + 1 < BOARD_WIDTH && y + 1 < BOARD_HEIGHT) {
			if (getBoard().getBoard()[x+1][y+1] == otherPlayerColour) {
				partValid = true;
			}
			int i = x + 1;
			int j = y + 1;
			while (i < BOARD_WIDTH && j < BOARD_HEIGHT) {
				if (getBoard().getBoard()[i][j] == EMPTY_SQUARE) {
					partValid = false;
				}
				if (getBoard().getBoard()[i][j] == playerColour && partValid == true) {
					validity = true;
				}
				i++;
				j++;
			}
		}
		return validity;
	}
	
	private boolean checkFlipSouthWest(int x, int y, Player player) {
		int playerColour = -1;
        int otherPlayerColour = -1;
        for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            if (getPlayer(i) == player) {
                playerColour = getPlayerColour(getPlayer(i));
            } else {
                otherPlayerColour = getPlayerColour(getPlayer(i));
            }
        }
        
        boolean validity = false;
        boolean partValid = false;
        
        if (x - 1 >= 0 && y + 1 < BOARD_HEIGHT) {
            if (getBoard().getBoard()[x-1][y+1] == otherPlayerColour) {
                partValid = true;
            }
            int i = x - 1;
            int j = y + 1;
            while (i >= 0 && j < BOARD_HEIGHT) {
                if (getBoard().getBoard()[i][j] == EMPTY_SQUARE) {
                    partValid = false;
                }
                if (getBoard().getBoard()[i][j] == playerColour
					&& partValid == true) {
                    validity = true;
                }
                i--;
                j++;
            }
        }
		return validity;
    }
	
	private boolean checkFlip(int x, int y, Player player) {
		boolean validity = false;
        
		// Checking if there is a flip above the position for the player
		if (validity == false) {
			validity = checkFlipNorth(x, y, player);
		}
		
		// Checking if there is a flip below the position for the player
		if (validity == false) {
			validity = checkFlipSouth(x, y, player);
		}
		
		// Checking if there is a flip left of the position for the player
		if (validity == false) {
			validity = checkFlipWest(x, y, player);
		}
        
		// Checking if there is a flip right of the position for the player
		if (validity == false) {
			validity = checkFlipEast(x, y, player);
		}
		
		// Checking if there is a flip above and left of the position for the player
		if (validity == false) {
			validity = checkFlipNorthWest(x, y, player);
		}
		
		// Checking if there is a flip above and right of the position for the player
		if (validity == false) {
			validity = checkFlipNorthEast(x, y, player);
		}
		
		// Checking if there is a flip below and right of the position for the player
		if (validity == false) {
			validity = checkFlipSouthEast(x, y, player);
		}
		
		// Checking if there is a flip below and left of the position for the player
		if (validity == false) {
        	validity = checkFlipSouthWest(x, y, player);
		}
		
		return validity;
	}
	
	public boolean checkValid(int x, int y, Player player){
		if (getBoard().getBoard()[x][y] == EMPTY_SQUARE) {
			return checkFlip(x,y,player);
		} else {
			return false;
		}
	}
	
	private void flipNorth(int x, int y, Player player) {
		if (checkFlipNorth(x, y, player) == true) {
			int playerColour = -1;
            int otherPlayerColour = -1;
            for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
                if (getPlayer(i) == player) {
                    playerColour = getPlayerColour(getPlayer(i));
                } else {
                    otherPlayerColour = getPlayerColour(getPlayer(i));
                }
            }
			boolean flipping = true;
			int i = y - 1;
			while (i >= 0 && flipping == true) {
				if (getBoard().getBoard()[x][i] == otherPlayerColour) {
					getBoard().setPiece(playerColour, x, i);
				} else {
					flipping = false;
				}
				i--;
			}
		}
	}
	
	private void flipSouth(int x, int y, Player player) {
		if (checkFlipSouth(x, y, player) == true) {
			int playerColour = -1;
            int otherPlayerColour = -1;
            for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
                if (getPlayer(i) == player) {
                    playerColour = getPlayerColour(getPlayer(i));
                } else {
                    otherPlayerColour = getPlayerColour(getPlayer(i));
                }
            }
			boolean flipping = true;
			int i = y + 1;
			while (i < BOARD_HEIGHT && flipping == true) {
				
				if (getBoard().getBoard()[x][i] == otherPlayerColour) {
					Piece flipped = new Piece(player.getColour());
					getBoard().setPiece(playerColour, x, i);
				} else {
					flipping = false;
				}
				i++;
			}
		}
	}
	
	private void flipWest(int x, int y, Player player) {
		if (checkFlipWest(x, y, player) == true) {
			int playerColour = -1;
            int otherPlayerColour = -1;
            for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
                if (getPlayer(i) == player) {
                    playerColour = getPlayerColour(getPlayer(i));
                } else {
                    otherPlayerColour = getPlayerColour(getPlayer(i));
                }
            }
			boolean flipping = true;
			int i = x - 1;
			while (i >= 0 && flipping == true) {
				if (getBoard().getBoard()[i][y] == otherPlayerColour) {
					Piece flipped = new Piece(player.getColour());
					getBoard().setPiece(playerColour, i, y);
				} else {
					flipping = false;
				}
				i--;
			}
		}
	}
    
	private void flipEast(int x, int y, Player player) {
		if (checkFlipEast(x, y, player) == true) {
			int playerColour = -1;
            int otherPlayerColour = -1;
            for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
                if (getPlayer(i) == player) {
                    playerColour = getPlayerColour(getPlayer(i));
                } else {
                    otherPlayerColour = getPlayerColour(getPlayer(i));
                }
            }
			boolean flipping = true;
			int i = x + 1;
			while (i < BOARD_WIDTH && flipping == true) {
				if (getBoard().getBoard()[i][y] == otherPlayerColour) {
					Piece flipped = new Piece(player.getColour());
					getBoard().setPiece(playerColour, i, y);
				} else {
					flipping = false;
				}
				i++;
			}
		}
	}
	
	private void flipNorthWest(int x, int y, Player player) {
		if (checkFlipNorthWest(x, y, player) == true) {
			int playerColour = -1;
            int otherPlayerColour = -1;
            for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
                if (getPlayer(i) == player) {
                    playerColour = getPlayerColour(getPlayer(i));
                } else {
                    otherPlayerColour = getPlayerColour(getPlayer(i));
                }
            }
			boolean flipping = true;
			int i = x - 1;
			int j = y - 1;
			while (i >= 0 && flipping == true) {
				
				if (getBoard().getBoard()[i][j] == otherPlayerColour) {
					Piece flipped = new Piece(player.getColour());
					getBoard().setPiece(playerColour, i, j);
				} else {
					flipping = false;
				}
				i--;
				j--;
			}
		}
	}
    
	private void flipNorthEast(int x, int y, Player player) {
		if (checkFlipNorthEast(x, y, player) == true) {
			int playerColour = -1;
            int otherPlayerColour = -1;
            for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
                if (getPlayer(i) == player) {
                    playerColour = getPlayerColour(getPlayer(i));
                } else {
                    otherPlayerColour = getPlayerColour(getPlayer(i));
                }
            }
			boolean flipping = true;
			int i = x + 1;
			int j = y - 1;
			while (j >= 0 && flipping == true) {
				if (getBoard().getBoard()[i][j] == otherPlayerColour) {
					Piece flipped = new Piece(player.getColour());
					getBoard().setPiece(playerColour, i, j);
				} else {
					flipping = false;
				}
				i++;
				j--;
			}
		}
	}
    
	private void flipSouthEast(int x, int y, Player player) {
		if (checkFlipSouthEast(x, y, player) == true) {
			int playerColour = -1;
            int otherPlayerColour = -1;
            for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
                if (getPlayer(i) == player) {
                    playerColour = getPlayerColour(getPlayer(i));
                } else {
                    otherPlayerColour = getPlayerColour(getPlayer(i));
                }
            }
			boolean flipping = true;
			int i = x + 1;
			int j = y + 1;
			while (j < BOARD_HEIGHT && flipping == true) {
				if (getBoard().getBoard()[i][j] == otherPlayerColour) {
					Piece flipped = new Piece(player.getColour());
					getBoard().setPiece(playerColour, i, j);
				} else {
					flipping = false;
				}
				i++;
				j++;
			}
		}
	}
	
	private void flipSouthWest(int x, int y, Player player) {
		if (checkFlipSouthWest(x, y, player) == true) {
			int playerColour = -1;
            int otherPlayerColour = -1;
            for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
                if (getPlayer(i) == player) {
                    playerColour = getPlayerColour(getPlayer(i));
                } else {
                    otherPlayerColour = getPlayerColour(getPlayer(i));
                }
            }
			boolean flipping = true;
			int i = x - 1;
			int j = y + 1;
			while (i >= 0 && flipping == true) {
				
				if (getBoard().getBoard()[i][j] == otherPlayerColour) {
					Piece flipped = new Piece(player.getColour());
					getBoard().setPiece(playerColour, i, j);
				} else {
					flipping = false;
				}
				i--;
				j++;
			}
		}
	}
	
	/*
     public static void main(String[] args) {
     // This is used for testing
     }
     */
	
	private final int NUMBER_OF_PLAYERS = 2;
	private final int BOARD_HEIGHT = 8;
	private final int BOARD_WIDTH = 8;
	private final int EMPTY_SQUARE = 0;
	private final int BLACK_PIECE = 1;
	private final int WHITE_PIECE = 2;
}