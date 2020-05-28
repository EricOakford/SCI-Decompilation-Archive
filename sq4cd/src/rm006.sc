;;; Sierra Script 1.0 - (do not remove this comment)
(script# 6)
(include game.sh)
(use Main)
(use SQRoom)
(use Sq4Feature)
(use MoveCyc)
(use Sound)
(use System)

(public
	rm006 0
)

(local
	[falconPath 50] = [1 0 114 70 1 0 116 70 1 0 118 70 1 1 119 70 1 1 123 70 1 2 133 70 1 2 142 70 1 3 162 71 1 4 197 71 1 5 239 71 1 6 314 72 0 0 -100 -100 -32768]
	[local50 8] = [0 1 3 2 4 5 6 99]
)
(procedure (localproc_0020 param1 &tmp temp0)
	(Animate (cast elements?) FALSE)
	(if param1
		(= temp0 0)
		(while (<= temp0 100)
			(Palette PALIntensity 8 15 temp0)
			(= temp0 (+ temp0 2))
		)
	else
		(= temp0 100)
		(while (>= temp0 0)
			(Palette PALIntensity 8 15 temp0)
			(= temp0 (- temp0 2))
		)
	)
)

(instance rm006 of SQRoom
	(properties
		picture 6
		style $0002
	)
	
	(method (init &tmp [temp0 50])
		(Load PICTURE 600)
		(Load VIEW 1 710)
		(Load SOUND 110)
		(Load SCRIPT MOVECYC)
		(super init:)
		(soundFX init:)
		(falcon init: z: 1000)
		(self setRegions: 707 setScript: falconScript)
	)
)

(instance falconScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
		(and (== state 2) (== (music prevSignal?) 20))
			(self cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= register 0)
				(= seconds 2)
			)
			(1
				(soundFX loop: 1 number: 110 play:)
				(falcon
					setPri: 8
					z: 0
					cycleSpeed: 5
					setCycle: MoveCycle @falconPath self
				)
			)
			(2 (falcon dispose:))
			(3
				(curRoom overlay: 600 3)
				(= seconds 8)
			)
			(4
				(curRoom drawPic: 6 WIPEUP)
				(Palette PALIntensity 8 15 0)
				(= seconds 1)
			)
			(5
				(credits init: loop: [local50 register] cel: 0)
				(credits
					x:
						(/
							(-
								320
								(CelWide
									(credits view?)
									(credits loop?)
									(credits cel?)
								)
							)
							2
						)
					y:
						(/
							(-
								190
								(CelHigh
									(credits view?)
									(credits loop?)
									(credits cel?)
								)
							)
							2
						)
				)
				(localproc_0020 1)
				(= cycles 1)
			)
			(6 (= seconds 3))
			(7
				(localproc_0020 0)
				(= cycles 4)
			)
			(8
				(if (== [local50 (++ register)] 99)
					(= cycles 1)
				else
					(= start 5)
					(self init:)
				)
			)
			(9
				(credits dispose:)
				(= cycles 2)
			)
			(10
				(Palette PALIntensity 8 15 100)
				(curRoom newRoom: 9)
			)
		)
	)
)

(instance falcon of Sq4Prop
	(properties
		view 1
	)
)

(instance credits of Sq4Prop
	(properties
		view 710
	)
)

(instance soundFX of Sound
	(properties)
)
