;;; Sierra Script 1.0 - (do not remove this comment)
(script# 9)
(include game.sh)
(use Main)
(use SQRoom)
(use Sq4Feature)
(use MoveCyc)
(use Sound)
(use System)

(public
	rm009 0
)

(local
	[mallardPath 53] = [0 0 126 37 0 1 129 37 0 2 131 41 0 3 136 47 0 4 144 60 0 5 154 71 0 6 159 80 0 6 162 88 0 6 166 96 0 6 169 104 0 6 173 113 0 6 177 124 0 0 -100 -100 -32768]
)
(instance rm009 of SQRoom
	(properties
		picture 9
	)
	
	(method (init)
		(Load VIEW 111)
		(super init:)
		(self setScript: mallardScript setRegions: INTRO)
	)
)

(instance mallardScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 100])
		(switch (= state newState)
			(0 (= seconds 4))
			(1
				(narrator
					modeless: 1
					returnVal: 0
					nMsgType: 3
					say: 1 self 2 64 2 5 67 315 25 myTextColor5 26 myLowlightColor 27 1 30 310
				)
			)
			(2
				(soundFX init: loop: 1 number: 110 play:)
				(= seconds 2)
			)
			(3
				(mallard init: cycleSpeed: 3 setCycle: MoveCycle @mallardPath self)
			)
			(4
				(mallard dispose:)
				(music fade: 0 3 2 1)
				(= seconds 4)
			)
			(5 (curRoom newRoom: 10))
		)
	)
)

(instance mallard of Sq4Prop
	(properties
		view 111
	)
	
	(method (doit)
		(self cycleSpeed: (self cel?))
		(super doit:)
	)
)

(instance soundFX of Sound
	(properties)
)
