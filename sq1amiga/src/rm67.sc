;;; Sierra Script 1.0 - (do not remove this comment)
(script# 67)
(include sci.sh)
(use Main)
(use Intrface)
(use SQRoom)
(use RandCyc)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use StopWalk)
(use Motion)
(use Actor)
(use System)

(public
	rm67 0
)

(local
	[local0 2]
)
(instance rm67 of SQRoom
	(properties
		picture 67
		style $0005
	)
	
	(method (init)
		(LoadMany 128 2 43 167)
		(self setRegions: 703)
		(self
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						117
						123
						112
						114
						132
						105
						157
						100
						185
						103
						197
						114
						191
						125
						176
						127
						165
						107
						149
						109
						169
						130
						135
						133
					yourself:
				)
				((Polygon new:)
					type: 2
					init:
						253
						130
						206
						101
						194
						102
						176
						93
						144
						97
						140
						101
						126
						105
						80
						104
						142
						143
						151
						151
						218
						189
						0
						189
						0
						0
						319
						0
						319
						185
					yourself:
				)
		)
		(cond 
			((Btst 52) 0)
			((Btst 51) (escapePod init:) (hood init: setCycle: End self))
			(else (escapePod init:) (hood init:))
		)
		(elevator init:)
		(elevatorDoor init: stopUpd:)
		(doDadOnWall init:)
		(ego observeControl: 16384)
		(super init:)
		(self setScript: roomControl)
	)
	
	(method (doit)
		(cond 
			((Btst 54) (Print 67 0) (curRoom newRoom: 71))
			((ego script?) 0)
			(
				(and
					(& (ego onControl: 1) $0002)
					(!= (ego priority?) 11)
				)
				(ego setPri: 11)
				(ego setScript: goingUp)
			)
			(
				(and
					(& (ego onControl: 1) $0400)
					(== (ego priority?) 11)
				)
				(ego setPri: -1)
			)
			((and (& (ego onControl: 0) $4000) (Btst 51)) (ego setScript: getInLaunchTube))
			(
			(and (& (ego onControl: 0) $4000) (not (ego script?))) (ego setScript: getInEscapePod))
		)
		(super doit: &rest)
	)
	
	(method (notify)
		(if (== prevRoomNum 66) (HandsOff))
	)
)

(instance roomControl of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego init: hide:)
				(= cycles 3)
			)
			(1
				(cond 
					((Btst 52) (self setScript: noEscapeForEgo))
					((Btst 51) (self setScript: waveGoodBye))
					(else (self setScript: madeItInTime))
				)
			)
		)
	)
)

(instance egoDropsIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(soundFx number: 530 loop: 1 play:)
				(elevator init: yStep: 8 setMotion: MoveTo 155 100 self)
			)
			(1
				(elevator yStep: 4 setMotion: MoveTo 155 110 self)
			)
			(2
				(elevator yStep: 2 setMotion: MoveTo 155 120 self)
			)
			(3
				(elevator yStep: 1 setMotion: MoveTo 155 128 self)
				(ego setPri: 11)
			)
			(4
				(soundFx number: 531 loop: 1 play: self)
			)
			(5
				(soundFx number: 311 loop: 1 play:)
				(elevatorDoor setCycle: End self)
			)
			(6
				(soundFx stop:)
				(elevator cel: 1 stopUpd: setPri: 8)
				(ego
					show:
					setPri: 11
					loop: 2
					cel: 0
					x: 156
					y: 113
					view: 2
					setCycle: StopWalk 62
					setStep: 4 2
				)
				(HandsOff)
				(= cycles 3)
			)
			(7
				(ego observeControl: 16384)
				(ego
					ignoreActors: 1
					setPri: 11
					setMotion: MoveTo 172 132 self
				)
			)
			(8
				(SolvePuzzle 1 173)
				(NormalEgo 2 2 62)
				(ego setPri: -1)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance goingUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 156 113 self)
			)
			(1
				(ego setHeading: 180)
				(= cycles 3)
			)
			(2
				(soundFx number: 311 loop: 1 play:)
				(elevatorDoor setCycle: Beg self)
			)
			(3
				(elevatorDoor stopUpd:)
				(ego hide: setPri: -1)
				(soundFx number: 530 loop: 1 play:)
				(elevator cel: 0 yStep: 2 setMotion: MoveTo 155 120 self)
			)
			(4
				(elevator yStep: 4 setMotion: MoveTo 155 110 self)
			)
			(5
				(elevator yStep: 8 setMotion: MoveTo 155 80 self)
			)
			(6
				(elevator yStep: 14 setMotion: MoveTo 155 60 self)
			)
			(7
				(elevator yStep: 20 setMotion: MoveTo 155 30 self)
			)
			(8
				(elevator setMotion: MoveTo 155 20 self)
			)
			(9
				(if (Btst 53) (Bset 51))
				(curRoom newRoom: 66)
			)
		)
	)
)

(instance madeItInTime of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(escapePod init:)
				(hood init:)
				(ego setScript: egoDropsIn self)
			)
			(1 (self dispose:))
		)
	)
)

(instance noEscapeForEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setScript: egoDropsIn self)
				(launchTube init:)
			)
			(1
				(ego ignoreControl: 16384)
				(= cycles 3)
			)
			(2 (self dispose:))
		)
	)
)

(instance waveGoodBye of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setScript: egoDropsIn)
				(escapePod init:)
				(hood init: setCycle: End self)
			)
			(1
				(soundFx number: 532 loop: 1 play:)
				(flame1 init: setCycle: RandCycle)
				(flame2 init: setCycle: RandCycle)
				(= cycles 15)
			)
			(2
				(soundFx number: 533 loop: 1 play:)
				(escapePod code: accelCode setMotion: MoveTo 525 99 self)
			)
			(3
				(soundFx loop: 0 fade:)
				(= cycles 30)
			)
			(4
				(Print 67 1)
				(launchTube init:)
				(ego ignoreControl: 16384)
				(Bset 52)
			)
		)
	)
)

(instance getInEscapePod of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= cycles 3)
				(ego ignoreControl: 16384)
			)
			(1
				(ego setMotion: PolyPath 192 139 self)
			)
			(2
				(ego setPri: 15 setMotion: MoveTo 192 139 self)
			)
			(3
				(ego
					posn: 192 129
					cycleSpeed: 3
					view: 43
					cel: 0
					setLoop: 0
					setCycle: End self
				)
			)
			(4
				(soundFx number: 324 loop: 1 play:)
				(hood ignoreControl: 4096 setCycle: End self)
			)
			(5
				(soundFx number: 369 loop: 1 play: self)
			)
			(6 (= ticks 60))
			(7
				(sounds eachElementDo: #stop)
				(soundFx number: 532 loop: 1 play:)
				(flame1
					init:
					setPri: 14
					ignoreControl: 4096
					setCycle: RandCycle
				)
				(flame2
					init:
					setPri: 14
					ignoreControl: 4096
					setCycle: RandCycle
				)
				(flyingEgo
					init:
					ignoreControl: 4096
					posn: (ego x?) (ego y?)
				)
				(ego hide:)
				(= cycles 15)
			)
			(8
				(ego hide:)
				(SolvePuzzle 3 174)
				(soundFx number: 533 loop: 1 play:)
				(escapePod
					ignoreActors: 1
					ignoreControl: 4096
					setMotion: MoveTo 265 144 self
				)
			)
			(9
				(escapePod setStep: 10 10 setMotion: MoveTo 275 142 self)
			)
			(10
				(escapePod setStep: 14 10 setMotion: MoveTo 295 140 self)
			)
			(11
				(escapePod setStep: 18 10 setMotion: MoveTo 315 136 self)
			)
			(12
				(escapePod setStep: 24 10 setMotion: MoveTo 335 132 self)
			)
			(13
				(escapePod setStep: 28 10 setMotion: MoveTo 355 128 self)
			)
			(14
				(escapePod setStep: 34 10 setMotion: MoveTo 375 124 self)
			)
			(15
				(soundFx loop: 0 fade:)
				(escapePod setStep: 40 20 setMotion: MoveTo 410 118 self)
			)
			(16
				(escapePod setStep: 50 30 setMotion: MoveTo 525 99 self)
			)
			(17 (curRoom newRoom: 71))
		)
	)
)

(instance getInLaunchTube of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (ego setHeading: 180 self))
			(1
				(ego view: 43 setLoop: 1 cel: 0 setCycle: End self)
			)
			(2 (Print 67 2) (= cycles 3))
			(3 (curRoom newRoom: 71))
		)
	)
)

(instance launchTube of Feature
	(properties
		description {escape pod launch tube}
		onMeCheck $1000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2 (Print 67 3))
			(3 (Print 67 4))
			(5 (Print 67 5))
			(12 (Print 67 6))
			(11 (Print 67 7))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance doDadOnWall of Feature
	(properties
		description {doDadOnWall}
		onMeCheck $0010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2 (Print 67 8))
			(3 (Print 67 9))
			(5 (Print 67 10))
			(12 (Print 67 11))
			(11 (Print 67 12))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance elevatorDoor of Prop
	(properties
		x 177
		y 115
		view 167
		priority 9
		signal $4010
		cycleSpeed 8
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2 (Print 67 13))
			(3 (Print 67 14))
			(5 (Print 67 15))
			(12 (Print 67 16))
			(11 (Print 67 17))
		)
	)
)

(instance elevator of Actor
	(properties
		x 155
		y 42
		onMeCheck $0800
		view 167
		loop 4
		priority 8
		signal $4810
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2 (Print 67 18))
			(3 (self setScript: goingUp))
			(5 (Print 67 19))
			(12 (Print 67 20))
			(11 (Print 67 21))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (onMe theObjOrX)
		(return
			(&
				onMeCheck
				(OnControl 4 (theObjOrX x?) (theObjOrX y?))
			)
		)
	)
)

(instance accelCode of Code
	(properties)
	
	(method (doit param1)
		(param1
			setStep: (+ (param1 xStep?) 2) (+ (param1 yStep?) 1)
		)
	)
)

(instance escapePod of Actor
	(properties
		x 225
		y 146
		yStep 8
		view 167
		loop 3
		priority 13
		signal $4810
		xStep 8
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2 (Print 67 22))
			(3
				(if (Btst 51)
					(Print 67 23)
				else
					(ego setScript: getInEscapePod)
				)
			)
			(5 (Print 67 24))
			(11 (Print 67 25))
			(12 (Print 67 26))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance hood of Actor
	(properties
		view 167
		loop 1
		priority 15
		signal $4810
		cycleSpeed 8
		moveSpeed 2
	)
	
	(method (doit)
		(self x: (escapePod x?))
		(self y: (escapePod y?))
		(super doit: &rest)
	)
)

(instance flame1 of Actor
	(properties
		view 167
		loop 2
		priority 11
		signal $4810
		cycleSpeed 2
		moveSpeed 2
	)
	
	(method (doit)
		(self x: (- (escapePod x?) 115))
		(self y: (+ (escapePod y?) 18))
		(super doit: &rest)
	)
)

(instance flame2 of Actor
	(properties
		view 167
		loop 2
		priority 11
		signal $4810
		cycleSpeed 2
		moveSpeed 2
	)
	
	(method (doit)
		(self x: (- (escapePod x?) 108))
		(self y: (+ (escapePod y?) 25))
		(super doit: &rest)
	)
)

(instance flyingEgo of Actor
	(properties
		view 43
		cel 7
		priority 15
		signal $4810
		cycleSpeed 2
		moveSpeed 2
	)
	
	(method (doit)
		(self x: (- (escapePod x?) 33))
		(self y: (- (escapePod y?) 17))
		(super doit: &rest)
	)
)
