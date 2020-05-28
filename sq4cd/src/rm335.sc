;;; Sierra Script 1.0 - (do not remove this comment)
(script# 335)
(include game.sh)
(use Main)
(use SQRoom)
(use Sq4Narrator)
(use Sq4Feature)
(use RandCyc)
(use ForCount)
(use LoadMany)
(use StopWalk)
(use Sound)
(use Motion)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	rm335 0
)

(instance rm335 of SQRoom
	(properties
		picture 335
	)
	
	(method (init)
		(LoadMany VIEW 0 4 402 14 336 331 335 339)
		(LoadMany SOUND 872 878)
		(tanks init:)
		(features add: tanks theArea eachElementDo: #init doit:)
		(switch prevRoomNum
			(330
				(zondra init:)
				(thoreen init:)
				(guard init:)
				(oxyTank init:)
				(ego init: posn: 110 158)
				(self setScript: strapScript)
			)
			(345
				(slug init: setScript: roarScript)
				(ego init: normal: FALSE moveHead: FALSE)
				(slugHead init:)
				(tongue init:)
				(oxyTank init:)
				(self setScript: slugScript)
			)
			(341
				(slug init:)
				(ego
					init:
					view: 336
					posn: 139 154
					loop: 0
					cel: 6
					normal: 0
					moveHead: 0
				)
				(slugHead init: z: 1000)
				(tongue init: z: 1000)
				(oxyTank init:)
				(self setScript: escapeScript)
			)
			(350
				(huggers init: setScript: hugScript)
				(slugHead init: setLoop: 3 x: 144 y: 91)
				(sinkSFX init:)
				(heroSFX init:)
				(ripples init:)
				(ego dispose:)
			)
			(else 
				(zondra init:)
				(thoreen init:)
				(guard init:)
				(oxyTank init:)
				(ego init: posn: 110 158)
				(self setScript: strapScript)
			)
		)
		(super init:)
	)
)

(instance slugScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 336
					illegalBits: 0
					setLoop: 0
					setCel: 6
					setPri: 10
					posn: 139 154
				)
				(zondra
					init:
					view: 336
					posn: 162 165
					setLoop: 1
					setCel: 0
				)
				(thoreen init:)
				(guard init: posn: 189 140 setLoop: 0 setCel: 0)
				(oxyTank init:)
				(= cycles 1)
			)
			(1
				(thoreenTalker
					init: 0 0 0
					say:
						7
						self
						2
						64
						2
						10
						25
						myTopBordColor
						26
						(VGAOrEGA myInsideColor myLowlightColor)
						27
						1
						67
						315
				)
			)
			(2
				(guard
					view: 335
					setLoop: 1
					xStep: 6
					yStep: 3
					setCycle: Walk
					illegalBits: 0
					setMotion: MoveTo 300 115 self
				)
				(zondra
					view: 335
					setLoop: 0
					setCycle: Walk
					illegalBits: 0
					xStep: 6
					yStep: 3
					setMotion: MoveTo 13 108
				)
				(thoreen
					view: 335
					setLoop: 2
					setCycle: Walk
					illegalBits: 0
					xStep: 6
					yStep: 3
					setMotion: MoveTo 18 185
				)
			)
			(3 (= cycles 7))
			(4 (curRoom newRoom: 350))
		)
	)
)

(instance roarScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 3))
			(1
				(slugHead setPri: 6 setCycle: EndLoop self)
			)
			(2
				(tongue setPri: 7 setCycle: ForwardCounter 5 self)
			)
			(3
				(tongue dispose:)
				(slugHead setCycle: BegLoop self)
				(= cycles 1)
			)
			(4 (roarScript dispose:))
		)
	)
)

(instance strapScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= cycles 1))
			(1
				(music hold: 2)
				(ego
					view: 0
					illegalBits: 0
					setPri: 9
					setCycle: Walk
					setMotion: MoveTo 139 154
				)
				(zondra
					setLoop: 2
					setCycle: Walk
					setPri: 7
					setMotion: MoveTo 162 165 self
				)
				(guard
					setLoop: 1
					setCycle: Walk
					setMotion: MoveTo 189 140 self
				)
			)
			(2
				(zondra view: 336 setLoop: 1 setCel: 0)
			)
			(3
				(guard view: 331 loop: 0 cel: 0)
				(= cycles 1)
			)
			(4
				(thoreenTalker
					init: 0 0 0
					say:
						1
						self
						2
						64
						2
						10
						25
						myTopBordColor
						26
						(VGAOrEGA myInsideColor myLowlightColor)
						27
						1
						67
						315
				)
			)
			(5 (= cycles 1))
			(6
				(ego
					normal: 0
					moveHead: 0
					illegalBits: 0
					view: 336
					setPri: 10
					setCycle: EndLoop self
				)
			)
			(7
				(thoreen cycleSpeed: 12 setCycle: EndLoop)
				(zondra cycleSpeed: 12 setCycle: EndLoop self)
			)
			(8
				(ego normal: TRUE moveHead: TRUE)
				(curRoom newRoom: 340)
			)
		)
	)
)

(instance escapeScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego posn: 139 154 setCycle: BegLoop self)
			)
			(1
				(ego
					view: 402
					setPri: 6
					normal: 1
					setCycle: Walk
					setMotion: MoveTo 181 152 self
				)
			)
			(2
				(slugHead z: 0 setLoop: 2)
				(tongue z: 0 setLoop: 0 setCycle: RandCycle)
				(= cycles 1)
			)
			(3
				(EgoHeadMove 14)
				(ego setCycle: StopWalk 14 self setLoop: 3)
				(= cycles 1)
			)
			(4
				(HandsOn)
				(User canControl: FALSE)
				(theIconBar disable: ICON_WALK)
				(= seconds 8)
			)
			(5
				(HandsOff)
				(self setScript: slugBaitScript)
			)
		)
	)
)

(instance oxyTankScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					normal: 0
					moveHead: 0
					view: 337
					setPri: 12
					setLoop: 3
					cel: 0
					setCycle: EndLoop
				)
				(= cycles 3)
			)
			(1
				(ego get: iOxygenTank)
				(oxyTank dispose:)
				(SolvePuzzle fGetOxygenTank 5)
				(= cycles 2)
			)
			(2
				(Inventory curIcon: (inventory at: iOxygenTank) hide:)
				(theGame setCursor: ((inventory curIcon?) cursor?) TRUE)
				(= cycles 1)
			)
			(3
				(self setScript: slugBaitScript)
			)
		)
	)
)

(instance slugBaitScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(tongue setCycle: 0)
				(slugHead setLoop: 2 cel: 0)
				(= cycles 1)
			)
			(1
				(tongue setLoop: 2 setCel: 0 setCycle: EndLoop)
				(= cycles 4)
			)
			(2 (= cycles 1))
			(3
				(Bset fSlugBait)
				(curRoom newRoom: 350)
			)
		)
	)
)

(instance hugScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ripples setCycle: ForwardCounter 2)
				(slugHead
					setPri: 3
					x: 144
					y: 91
					setMotion: MoveTo 144 130 self
				)
				(= ticks 3)
			)
			(1
				(sinkSFX play:)
				(ripples setLoop: 5 setCel: 0 setCycle: EndLoop)
			)
			(2
				(sinkSFX dispose:)
				(ripples dispose:)
				(slugHead dispose:)
				(heroSFX play:)
				(= ticks 3)
			)
			(3
				(vibraTalker
					modNum: 322
					say: 3 self 2 64 130 10 25 myTextColor20 26 myLowlightColor 27 1 67 315
				)
			)
			(4
				(zondra
					init:
					view: 339
					setLoop: 1
					setCel: 0
					setCycle: Walk
					x: 2
					y: 107
					xStep: 6
					yStep: 3
					setMotion: MoveTo 137 106 self
				)
				(guard
					init:
					view: 339
					setLoop: 2
					setCel: 0
					setCycle: Walk
					x: 318
					y: 108
					xStep: 6
					yStep: 3
					setMotion: MoveTo 183 108 self
				)
				(thoreen
					init:
					view: 339
					setLoop: 3
					setCel: 1
					setCycle: Walk
					x: 0
					y: 215
					xStep: 6
					yStep: 4
					illegalBits: 0
					setMotion: MoveTo 130 120 self
				)
			)
			(5 0)
			(6
				(huggers setCel: 2 setCycle: EndLoop)
				(zondra dispose:)
				(guard dispose:)
			)
			(7
				(thoreen z: 1000)
				(= cycles 20)
			)
			(8 (= seconds 2))
			(9
				(huggers setCycle: CycleTo 4 -1 self)
			)
			(10
				(thoreen
					view: 336
					setLoop: 2
					setCel: 0
					x: 137
					y: 116
					z: 0
				)
				(= seconds 2)
			)
			(11
				(zondraTalker modNum: 322 say: 13 self)
			)
			(12 (= cycles 2))
			(13 (zondraTalker say: 14 self))
			(14 (= cycles 2))
			(15
				(zondraTalker disposeWhenDone: 1 say: 15 self)
			)
			(16 (= cycles 2))
			(17 (rogerTalker say: 5 self))
			(18 (= cycles 2))
			(19
				(zondraTalker disposeWhenDone: 0 modNum: 322 say: 16 self)
			)
			(20 (= cycles 2))
			(21
				(zondraTalker disposeWhenDone: 1 say: 17 self)
				(heroSFX fade:)
			)
			(22 (= cycles 2))
			(23 (= ticks 2))
			(24 (curRoom newRoom: 355))
		)
	)
)

(instance sinkSFX of Sound
	(properties
		number 872
	)
)

(instance heroSFX of Sound
	(properties
		flags $0001
		number 878
		loop -1
	)
)

(instance zondra of Sq4Actor
	(properties
		x 100
		y 110
		sightAngle 90
		view 331
		loop 2
		priority 6
		signal (| ignrAct fixPriOn)
		lookStr 1
	)
)

(instance thoreen of Sq4Actor
	(properties
		x 124
		y 169
		sightAngle 90
		view 336
		loop 2
		priority 9
		signal (| ignrAct fixPriOn)
		lookStr 2
	)
)

(instance guard of Sq4Actor
	(properties
		x 68
		y 134
		sightAngle 90
		view 331
		loop 1
		priority 6
		signal (| ignrAct fixPriOn)
		lookStr 3
	)
)

(instance tanks of Sq4Feature
	(properties
		x 207
		y 141
		nsTop 129
		nsLeft 195
		nsBottom 154
		nsRight 219
		sightAngle 180
		lookStr 4
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(if (== ((inventory at: iOxygenTank) owner?) 335)
					(curRoom setScript: oxyTankScript)
				else
					(super doVerb: theVerb)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance tongue of Sq4Prop
	(properties
		x 157
		y 88
		view 337
		priority 7
		signal (| ignrAct fixPriOn)
		lookStr 5
	)
)

(instance slug of Sq4Prop
	(properties
		x 158
		y 96
		sightAngle 180
		view 342
		priority 5
		signal fixPriOn
		lookStr 6
	)
)

(instance slugHead of Sq4Actor
	(properties
		x 158
		y 96
		sightAngle 180
		view 342
		loop 2
		priority 6
		signal (| ignrAct fixPriOn)
		lookStr 7
	)
)

(instance huggers of Sq4Prop
	(properties
		x 161
		y 109
		view 339
		priority 7
		signal (| ignrAct fixPriOn)
	)
)

(instance ripples of Sq4Prop
	(properties
		x 143
		y 72
		view 342
		loop 4
	)
)

(instance oxyTank of Sq4Prop
	(properties
		x 204
		y 153
		sightAngle 90
		view 336
		loop 3
		priority 11
		signal (| ignrAct fixPriOn)
		lookStr 8
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(if (== ((inventory at: iOxygenTank) owner?) 335)
					(curRoom setScript: oxyTankScript)
				else
					(super doVerb: theVerb)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance theArea of Sq4Feature
	(properties
		x 156
		y 10
		nsBottom 200
		nsRight 320
		sightAngle 180
		lookStr 9
	)
)

(instance zOrifice of Prop
	(properties
		x 148
		y 71
		view 1999
		loop 6
		priority 15
		signal fixPriOn
	)
)

(instance eOrifice of Prop
	(properties
		x 161
		y 68
		view 1999
		loop 4
		priority 15
		signal fixPriOn
	)
)

(instance thoreenTalker of FaceTalker
	(properties
		modNum 322
		talkerNum THOREEN
		tpType 2
	)
)

(instance vibraTalker of Sq4Narrator
	(properties
		noun 20
		modNum 322
		talkerNum VIBRA
		tpType 2
	)
)

(instance zondraTalker of Sq4Talker
	(properties
		noun ZONDRA
		modNum 322
		view 1332
		disposeWhenDone 0
		talkerNum ZONDRA
		mouthOffsetX 23
		mouthOffsetY 36
		eyeOffsetX 24
		eyeOffsetY 22
		tStyle 1
	)
)

(instance rogerTalker of Sq4Talker
	(properties
		noun ROGER
		modNum 322
		view 1008
		talkerNum ROGER
		mouthOffsetX 21
		mouthOffsetY 32
		eyeOffsetX 27
		eyeOffsetY 21
	)
)
