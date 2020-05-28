;;; Sierra Script 1.0 - (do not remove this comment)
(script# 535)
(include game.sh)
(use Main)
(use SQRoom)
(use Sq4Feature)
(use PolyPath)
(use Polygon)
(use LoadMany)
(use DPath)
(use Sound)
(use Motion)
(use System)

(public
	rm535 0
)

(instance rm535 of SQRoom
	(properties
		picture 535
		east 540
		west 530
	)
	
	(method (init)
		(LoadMany VIEW 535 300 7)
		(LoadMany SOUND 131 156 104 158 535 107)
		(switch prevRoomNum
			(120
				(self setScript: shipScript)
				(ego posn: 160 110)
				(globalSound hold: 0)
			)
			(east
				(HandsOff)
				(if (> (ego y?) 134) (ego y: 134))
				(self setScript: enterScript style: 11)
			)
			(west
				(HandsOff)
				(ego y: (if (> (ego y?) 120) 120 else (ego y?)))
				(self setScript: enterScript style: 12)
			)
			(else 
				(ego x: 160 y: 140)
				(self style: 100)
				(= prevRoomNum west)
			)
		)
		(super init:)
		(ego view: 0 init:)
		(theRailView init:)
		(theLeftPump init:)
		(theRightPump init:)
		(theShuttle init:)
		(self setRegions: LANDING)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init: 319 189 284 134 319 134
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 0 129 21 129 25 120 64 120 75 129 64 136 37 136 0 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 0 52 0 0 319 0 319 57 276 54 250 54 178 54 163 76 149 55
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 79 109 139 109 139 128 106 128 90 128 79 123
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 189 109 247 109 247 128 220 128 189 128
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 242 63 274 63 274 73 242 73
					yourself:
				)
		)
		(if (== prevRoomNum 120)
			(HandsOff)
			(leftEngine setLoop: 0 setCycle: Forward init:)
			(rightEngine setLoop: 0 setCycle: Forward init:)
			(leftGear x: 145 z: 22 setCel: 3 init:)
			(rightGear x: 182 z: 22 setCel: 3 init:)
		else
			(leftEngine init: setLoop: 2 setCel: 11 stopUpd:)
			(rightEngine init: setLoop: 2 setCel: 10 stopUpd:)
			(leftGear x: 123 z: 0 setCel: 0 init: stopUpd:)
			(rightGear x: 204 z: 0 setCel: 0 init: stopUpd:)
		)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(script)
			((and (< (ego x?) 20) (< (ego y?) 144)) (HandsOff) (curRoom newRoom: 530))
			((and (> (ego x?) 300) (< (ego y?) 144)) (HandsOff) (curRoom newRoom: 540))
		)
	)
)

(instance leftEngine of Sq4Prop
	(properties
		x 150
		y 77
		view 535
		priority 15
		signal (| ignrAct fixPriOn)
		lookStr 1
	)
)

(instance rightEngine of Sq4Prop
	(properties
		x 178
		y 77
		view 535
		priority 15
		signal (| ignrAct fixPriOn)
		lookStr 1
	)
)

(instance leftGear of Sq4Actor
	(properties
		y 72
		view 535
		loop 3
		cel 3
		priority 8
		signal (| ignrAct ignrHrz fixPriOn)
		illegalBits $0000
		lookStr 2
	)
	
	(method (doit)
		(super doit:)
		(cond 
			((> z 21))
			((> z 0) (-- z) (-- x))
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(HandsOff)
				(curRoom setScript: toXenon)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance rightGear of Sq4Actor
	(properties
		y 72
		view 535
		loop 4
		cel 3
		priority 8
		signal (| ignrAct ignrHrz fixPriOn)
		illegalBits $0000
		lookStr 2
	)
	
	(method (doit)
		(super doit:)
		(cond 
			((> z 21))
			((> z 0) (++ x) (-- z))
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(HandsOff)
				(curRoom setScript: toXenon)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance sp of Sq4Actor
	(properties
		x 157
		y 83
		view 7
	)
)

(instance enterScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== prevRoomNum (curRoom west?))
					(ego init: x: 5 setMotion: MoveTo 40 (ego y?) self)
				else
					(ego init: x: 310 setMotion: MoveTo 280 (ego y?) self)
				)
			)
			(1
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance shipScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 3))
			(1
				(if (!= (globalSound prevSignal?) -1)
					(globalSound fade: self)
				else
					(= seconds 3)
				)
			)
			(2
				(= seconds 0)
				(music stop:)
				(globalSound number: 104 loop: 0 play:)
				(leftEngine setLoop: 2 setCel: 0 setCycle: EndLoop self)
				(rightEngine setLoop: 2 setCel: 0 setCycle: EndLoop)
			)
			(3
				(leftEngine
					setLoop: 2
					setCel: 0
					cycleSpeed: 12
					setCycle: EndLoop
				)
				(rightEngine setLoop: 2 setCel: 0 setCycle: CycleTo 9 1)
				(= ticks 60)
			)
			(4
				(aSound number: 158 loop: -1 play:)
				(leftGear z: 21 setCycle: CycleTo 1 -1)
				(rightGear z: 21 setCycle: CycleTo 1 -1 self)
			)
			(5 (= ticks 60))
			(6
				(aSound number: 156 loop: 0 play:)
				(leftGear setCel: 0)
				(rightGear setCel: 0)
				(leftEngine setLoop: 1)
				(rightEngine setLoop: 1)
				(= ticks 30)
			)
			(7
				(= seconds 0)
				(globalSound
					stop:
					number: 535
					vol: 127
					loop: -1
					flags: 1
					play:
				)
				(sp
					init:
					setCycle: Walk
					setMotion: DPath 139 69 -5 80 self
				)
			)
			(8
				(leftGear stopUpd:)
				(rightGear stopUpd:)
				(leftEngine stopUpd:)
				(rightEngine stopUpd:)
				(ego
					setSpeed: speed
					x: 160
					y: 100
					setMotion: PolyPath 210 145 self
				)
			)
			(9 (HandsOn) (self dispose:))
		)
	)
)

(instance toXenon of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if
		(and (== state 10) (== (globalSound prevSignal?) -1))
			(self cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 160 110 self)
			)
			(1 (narrator say: 3 self))
			(2
				(sp
					init:
					x: -5
					y: 80
					setCycle: Walk
					setMotion: PolyPath 140 85 self
				)
			)
			(3 (= seconds 3))
			(4
				(leftEngine cycleSpeed: 6 setLoop: 0 setCycle: Forward)
				(rightEngine cycleSpeed: 6 setLoop: 0 setCycle: Forward)
				(aSound number: 107 loop: 1 play:)
				(= seconds 4)
			)
			(5
				(aSound init: number: 158 loop: -1 play:)
				(leftGear
					setCel: 0
					setLoop: 3
					setMotion: MoveTo 145 51 self
				)
				(rightGear
					setCel: 0
					setLoop: 4
					setMotion: MoveTo 182 51 self
				)
			)
			(6 0)
			(7
				(leftGear setCycle: EndLoop self)
				(rightGear setCycle: EndLoop self)
			)
			(8 0)
			(9
				(aSound number: 109 loop: -1 play:)
				(= seconds 2)
			)
			(10
				(globalSound fade:)
				(leftGear hide:)
				(rightGear hide:)
				(= cycles 2)
			)
			(11 (sp dispose:) (= cycles 2))
			(12
				(globalSound fade:)
				(client setScript: 0)
				(curRoom newRoom: 119)
			)
		)
	)
)

(instance theLeftPump of Sq4Feature
	(properties
		x 53
		y 110
		nsBottom 189
		nsRight 319
		onMeCheck $0010
		lookStr 4
	)
)

(instance theRightPump of Sq4Feature
	(properties
		x 264
		y 58
		nsBottom 189
		nsRight 319
		onMeCheck $0020
		lookStr 5
	)
)

(instance theShuttle of Sq4Feature
	(properties
		x 170
		y 90
		nsBottom 189
		nsRight 319
		onMeCheck $0040
		lookStr 6
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(HandsOff)
				(curRoom setScript: toXenon)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance theRailView of Sq4Feature
	(properties
		x 161
		y 32
		nsTop 15
		nsLeft 37
		nsBottom 49
		nsRight 285
		sightAngle 90
		lookStr 7
	)
)

(instance aSound of Sound
	(properties)
)
