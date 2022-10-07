;;; Sierra Script 1.0 - (do not remove this comment)
(script# 60)
(include sci.sh)
(use Main)
(use Kq6Procs)
(use Game)
(use System)

(public
	rMist 0
)

(class rMist of Region
	(properties
		script 0
		number 0
		modNum -1
		noun 0
		timer 0
		keep 0
		initialized 0
		seenFirstMessage 0
		musicPlaying 0
	)
	
	(method (init)
		(super init: &rest)
		(if
			(and
				(or (== curRoomNum 550) (== curRoomNum 560))
				(not (Btst 14))
			)
			(if (not musicPlaying)
				(theMusic number: 551 loop: -1 play:)
				(= musicPlaying 1)
			)
			(self setScript: hintDrums)
		)
		(if (== curRoomNum 580) (theMusic stop:))
	)
	
	(method (dispose)
		(theMusic stop:)
		(Bset 25)
		(super dispose:)
	)
	
	(method (newRoom n)
		(= keep (OneOf n 550 560 570 580))
		(= initialized 0)
		(super newRoom: n &rest)
	)
)

(instance hintDrums of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 3))
			(1
				(cond 
					((Btst 74) (self dispose:))
					((curRoom script?) (self init:))
					((== curRoomNum 550) (messager say: 1 0 5 1 self))
					((== curRoomNum 560) (messager say: 1 0 1 1 self))
				)
			)
			(2 (= seconds 27))
			(3 (self init:))
		)
	)
)
