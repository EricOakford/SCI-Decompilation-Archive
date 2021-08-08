;;; Sierra Script 1.0 - (do not remove this comment)
(script# 763)
(include game.sh)
(use Main)
(use Intrface)
(use Language)
(use Sound)
(use System)

(public
	cartoonCode 0
)

(instance cartoonCode of Code

	(method (doit &tmp oldPause oldCur)
		(= oldCur (theGame setCursor: normalCursor))
		(= oldPause (Sound pause: TRUE))
		(SpeakAudio 9249 0 1)
		(switch
			(Print 763 0
				#width (proc932_2 240 280)
				#button {Watch it} 0
				#button {Skip it} 1
			)
			(0
				(theGame setCursor: oldCur)
			)
			(1
				(Sound fade:)
				(cond 
					(
						(OneOf curRoomNum
							100 101 102 103 104 114 105 106 107 108 109
							650 651 652 653 654 655 656 657 658 659 700
						)
						(theIntroToon doit:)
					)
					((OneOf curRoomNum 81 82 83 84 215 681)
						(elfToon doit:)
					)
					((OneOf curRoomNum 92 39 682)
						(icebellaToon doit:)
					)
					(
						(OneOf curRoomNum
							46 111 112 110 113 89 51
							660 661 662 663
						)
						(hermitToon doit:)
					)
					((OneOf curRoomNum 57 96 683)
						(cassimaToon doit:)
					)
					((OneOf curRoomNum 77 680)
						(gypsyToon doit:)
					)
					((OneOf curRoomNum 120 121 122 123 670 671 672 673 701)
						(endingToon doit:)
					)
				)
			)
		)
		(DoAudio Stop)
		(Sound pause: oldPause)
		(DisposeScript 763)
	)
)

(instance theIntroToon of Code
	
	(method (doit)
		(theGame restart: FALSE)
	)
)

(instance icebellaToon of Code

	(method (doit)
		(if (Btst fMetYeti)
			(Bset fDefeatedYeti)
		)
		(theIconBar enable:
			ICON_WALK
			ICON_LOOK
			ICON_DO
			ICON_TALK
		)
		(theMusic stop:)
		(curRoom newRoom: 35)
	)
)

(instance gypsyToon of Code

	(method (doit)
		(if (not (ego has: iAmulet))
			(ego get: iAmulet)
			(SolvePuzzle 2)
		)
		(curRoom newRoom: 13)
	)
)

(instance elfToon of Code
	
	(method (doit)
		(if (not (ego has: iElfShoes))
			(ego get: iElfShoes)
			(Bset fGotElfShoes)
			(SolvePuzzle 4)
		)
		(curRoom newRoom: 8)
	)
)

(instance cassimaToon of Code
	
	(method (doit)
		(ego setCycle: KQ5SyncWalk setStep: 3 2 view: 0)
		(Bset fCassimaCartoon)
		(= curRoomNum -1)
		(curRoom newRoom: 57)
	)
)

(instance hermitToon of Code
	
	(method (doit)
		(Bset fHermitCartoon)
		(= curRoomNum -1)
		(curRoom newRoom: 51)
	)
)

(instance endingToon of Code
	
	(method (doit)
		(curRoom newRoom: 673)
	)
)
