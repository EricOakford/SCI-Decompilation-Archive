;;; Sierra Script 1.0 - (do not remove this comment)
(script# 321)
(include game.sh)
(use Main)
(use SQRoom)
(use Sq4Narrator)
(use Sq4Feature)
(use Motion)
(use System)

(public
	rm321 0
)

(local
	[propellerPosn 12] = [142 115 144 88 108 114 109 87 80 114 81 86]
	[propeller 7]
)
(instance rm321 of SQRoom
	(properties
		picture 321
	)
	
	(method (init &tmp i)
		(= i 0)
		(while (<= i 5)
			;problem code blow
			;((= [propeller i] (Clone Propeller1))
			;corrected code below
			(= [propeller i] (Clone Propeller1))
			([propeller i]
				init:
				x: [propellerPosn (* i 2)]
				y: [propellerPosn (+ (* i 2) 1)]
				setCycle: Forward
			)
			(++ i)
		)
		(super init:)
		(Load VIEW 322)
		(spotRight init: setCycle: Forward)
		(theSub init:)
		(spotLeft init: setCycle: Forward)
		(eel init: setScript: eelScript)
		(self setScript: displayScript)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(roomNar modNum: 329 say: 2)
				(roomNar modNum: 321)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance displayScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOn)
				(theIconBar disable:
					ICON_WALK
					ICON_TASTE
					ICON_SMELL
					ICON_TALK
					ICON_DO
					ICON_ITEM
					ICON_INVENTORY
				)
				(= seconds 5)
			)
			(1 (HandsOff) (= cycles 1))
			(2
				(roomNar
					modNum: 321
					init: 0 0 0
					say:
						1
						self
						2
						64
						2
						2
						25
						myTextColor6
						26
						(VGAOrEGA myInsideColor myLowlightColor)
						27
						1
						67
						315
				)
			)
			(3
				(if (not (== (DoAudio 6) -1)) (-- state))
				(= cycles 1)
			)
			(4
				(HandsOn)
				(theIconBar disable:
					ICON_WALK
					ICON_TASTE
					ICON_SMELL
					ICON_TALK
					ICON_DO
					ICON_ITEM
					ICON_INVENTORY
				)
				(= cycles 1)
			)
			(5 (= seconds 7))
			(6
				(HandsOff)
				(zondraVoice
					say:
						1
						self
						2
						64
						2
						2
						25
						(VGAOrEGA myTextColor23 zondraTextColor)
						26
						(VGAOrEGA myInsideColor myLowlightColor)
						27
						1
						67
						315
				)
			)
			(7
				(if (not (== (DoAudio 6) -1)) (-- state))
				(= cycles 1)
			)
			(8
				(HandsOn)
				(theIconBar disable: 
					ICON_WALK
					ICON_TASTE
					ICON_SMELL
					ICON_TALK
					ICON_DO
					ICON_ITEM
					ICON_INVENTORY
				)					
				(= cycles 1)
			)
			(9 (= cycles 100))
			(10
				(zondraVoice
					say: 2 self 2 64 2 2 25 myTextColor23 26 myInsideColor 27 1 67 315
				)
			)
			(11
				(HandsOff)
				(cave init: setLoop: 4 setCycle: EndLoop self)
			)
			(12
				(if (IsObject zondraVoice) (zondraVoice dispose:))
				(if (IsObject roomNar) (roomNar dispose:))
				(curRoom newRoom: 329)
			)
		)
	)
)

(instance eelScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(eel
					setLoop: 6
					setCycle: Forward
					setMotion: MoveTo 50 220 self
				)
			)
			(1 (eel z: 1000) (= seconds 7))
			(2
				(jellyFish
					init:
					setLoop: 5
					illegalBits: 0
					setCycle: Forward
					ignoreHorizon:
					setMotion: MoveTo -10 -10 self
				)
			)
			(3
				(jellyFish dispose:)
				(= seconds 5)
			)
		)
	)
)

(instance spotLeft of Sq4Prop
	(properties
		x 99
		y 170
		sightAngle 180
		view 322
		loop 1
		signal ignrAct
		cycleSpeed 12
		lookStr 2
	)
)

(instance spotRight of Sq4Prop
	(properties
		x 307
		y 161
		sightAngle 180
		view 322
		signal ignrAct
		cycleSpeed 12
		lookStr 2
	)
)

(instance eel of Sq4Actor
	(properties
		x 345
		y 99
		sightAngle 180
		yStep 4
		view 322
		loop 6
		signal ignrAct
		xStep 6
		lookStr 3
	)
)

(instance jellyFish of Sq4Actor
	(properties
		x 325
		y 137
		sightAngle 180
		view 322
		loop 5
		signal ignrAct
		cycleSpeed 18
		xStep 6
		lookStr 4
	)
)

(instance cave of Sq4Prop
	(properties
		x 293
		y 65
		sightAngle 180
		view 322
		loop 4
		cycleSpeed 18
		lookStr 5
	)
)

(instance Propeller1 of Sq4Prop
	(properties
		x -100
		y -100
		sightAngle 180
		view 322
		loop 2
		lookStr 6
	)
)

(instance theSub of Sq4Feature
	(properties
		x 156
		y 28
		z -60
		nsTop 48
		nsLeft 60
		nsBottom 128
		nsRight 252
		sightAngle 180
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(roomNar modNum: 329 say: 1)
				(roomNar modNum: curRoomNum)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance zondraVoice of Sq4GlobalNarrator
	(properties
		modNum 321
		modeless TRUE
		talkerNum ZONDRA
	)
)

(instance roomNar of Sq4GlobalNarrator
	(properties
		noun NARRATOR
		modNum 321
		modeless TRUE
	)
)
