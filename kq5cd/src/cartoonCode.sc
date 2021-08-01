;;; Sierra Script 1.0 - (do not remove this comment)
(script# 763)
(include sci.sh)
(use Main)
(use Intrface)
(use Language)
(use Sound)
(use System)

(public
	cartoonCode 0
)

(instance cartoonCode of Code
	(properties)
	
	(method (doit &tmp temp0 temp1)
		(= temp1 (theGame setCursor: normalCursor))
		(= temp0 (Sound pause: 1))
		(SpeakAudio 9249 0 1)
		(switch
			(Print
				763
				0
				#width
				(proc932_2 240 280)
				#button
				{Watch it}
				0
				#button
				{Skip it}
				1
			)
			(0 (theGame setCursor: temp1))
			(1
				(Sound fade:)
				(cond 
					(
						(OneOf
							curRoomNum
							100
							101
							102
							103
							104
							114
							105
							106
							107
							108
							109
							650
							651
							652
							653
							654
							655
							656
							657
							658
							659
							700
						)
						(theIntroToon doit:)
					)
					((OneOf curRoomNum 81 82 83 84 215 681) (elfToon doit:))
					((OneOf curRoomNum 92 39 682) (icebellaToon doit:))
					(
						(OneOf
							curRoomNum
							46
							111
							112
							110
							113
							89
							51
							660
							661
							662
							663
						)
						(hermitToon doit:)
					)
					((OneOf curRoomNum 57 96 683) (cassimaToon doit:))
					((OneOf curRoomNum 77 680) (gypsyToon doit:))
					(
					(OneOf curRoomNum 120 121 122 123 670 671 672 673 701) (endingToon doit:))
				)
			)
		)
		(DoAudio 3)
		(Sound pause: temp0)
		(DisposeScript 763)
	)
)

(instance theIntroToon of Code
	(properties)
	
	(method (doit)
		(theGame restart: 0)
	)
)

(instance icebellaToon of Code
	(properties)
	
	(method (doit)
		(if (Btst 40) (Bset 57))
		(theIconBar enable: 0 1 2 3)
		(theMusic stop:)
		(curRoom newRoom: 35)
	)
)

(instance gypsyToon of Code
	(properties)
	
	(method (doit)
		(if (not (ego has: 27)) (ego get: 27) (SolvePuzzle 2))
		(curRoom newRoom: 13)
	)
)

(instance elfToon of Code
	(properties)
	
	(method (doit)
		(if (not (ego has: 33))
			(ego get: 33)
			(Bset 46)
			(SolvePuzzle 4)
		)
		(curRoom newRoom: 8)
	)
)

(instance cassimaToon of Code
	(properties)
	
	(method (doit)
		(ego setCycle: KQ5SyncWalk setStep: 3 2 view: 0)
		(Bset 111)
		(= curRoomNum -1)
		(curRoom newRoom: 57)
	)
)

(instance hermitToon of Code
	(properties)
	
	(method (doit)
		(Bset 112)
		(= curRoomNum -1)
		(curRoom newRoom: 51)
	)
)

(instance endingToon of Code
	(properties)
	
	(method (doit)
		(curRoom newRoom: 673)
	)
)
