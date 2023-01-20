;;; Sierra Script 1.0 - (do not remove this comment)
(script# 33)
(include sci.sh)
(use Main)
(use Intrface)
(use SQRoom)
(use DScript)
(use RandCyc)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm33 0
)

(local
	local0
	local1
	local2
	oldEgoX
	oldEgoY
)
(instance rm33 of SQRoom
	(properties
		picture 33
		style $000b
		east 32
	)
	
	(method (init)
		(LoadMany 128 23 133 946)
		(ego init: illegalBits: 0)
		(self
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						0
						0
						319
						0
						319
						47
						314
						47
						269
						47
						223
						50
						180
						39
						109
						29
						36
						37
						12
						67
						6
						92
						12
						136
						48
						152
						83
						185
						319
						185
						319
						189
						0
						189
					yourself:
				)
				((Polygon new:)
					type: 2
					init:
						73
						123
						40
						88
						48
						71
						67
						65
						99
						55
						129
						60
						272
						60
						319
						60
						319
						129
						277
						124
						194
						124
						178
						129
						169
						137
						105
						152
						88
						145
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 266 139 319 135 319 156 266 148
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 242 128 271 128 271 135 242 135
					yourself:
				)
		)
		(if (Btst 46)
			(laserEnd1 init: cel: 7)
			(laserEnd2 init: cel: 7)
		else
			(theMusic2 number: 427 loop: -1 flags: 1 play:)
			(LoadMany 132 459 428 429 432 427)
			(laser init: setCycle: Fwd)
			(laserThing1 init:)
			(laserThing2 init:)
		)
		(super init:)
		(HandsOn)
	)
	
	(method (doit)
		(cond 
			(script (script doit:))
			((& (ego onControl:) $0002) (= currentFloor 2))
			((& (ego onControl:) $0004) (= currentFloor 1))
			(
			(and (& (ego onControl: 1) $0010) (not (Btst 46))) (self setScript: chopUpEgo))
		)
		(super doit:)
	)
	
	(method (dispose)
		(theMusic2 stop: flags: 0)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2
				(Printf
					{You are in left-most room of this cavern. Standing sentinel at the juncture of the lower and upper pathways are two odd-looking units obviously placed there on purpose. %s.}
					(if (Btst 46)
						{They are no longer in operation thanks to you.}
					else
						{Something is being emitted by the devices.}
					)
				)
			)
			(3 (Print 33 0))
			(5 (Print 33 1))
			(12 (Print 33 2))
			(11 (Print 33 3))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance laserEnd1 of Prop
	(properties
		x 109
		y 113
		view 133
		loop 1
		priority 7
		signal $4010
		cycleSpeed 2
	)
)

(instance laserEnd2 of Prop
	(properties
		x 45
		y 152
		view 133
		loop 2
		priority 14
		signal $4010
		cycleSpeed 2
	)
)

(instance smoke1 of Prop
	(properties
		x 45
		y 152
		view 133
		loop 3
		signal $4000
		cycleSpeed 2
	)
)

(instance smoke2 of Prop
	(properties
		x 109
		y 108
		view 133
		loop 4
		signal $4000
		cycleSpeed 2
	)
)

(instance egoBlowItUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 110 170 self)
			)
			(1
				(ego setMotion: MoveTo 106 164 self)
			)
			(2
				(ego
					view: 23
					cycleSpeed: 2
					moveSpeed: 2
					setLoop: 0
					cel: 0
					setCycle: End self
				)
			)
			(3 (= cycles 4))
			(4
				(laserEnd1 init: cycleSpeed: 5 setCycle: CT 3 1)
				(laserEnd2 init: cycleSpeed: 5 setCycle: CT 3 1 self)
			)
			(5
				(soundFx number: 459 loop: 1 play: self)
			)
			(6
				(soundFx number: 428 loop: 0 play:)
				(laser hide:)
				(theMusic2 loop: 0 stop:)
				(ego setCycle: Beg self)
				(smoke1 init: cycleSpeed: 5 setCycle: End)
				(smoke2 init: cycleSpeed: 5 setCycle: End self)
			)
			(7
				(smoke1 dispose:)
				(smoke2 dispose:)
				(= cycles 1)
			)
			(8
				(laserEnd1 setCycle: End)
				(laserEnd2 setCycle: End self)
				(NormalEgo 1 1 61)
			)
			(9
				(SolvePuzzle 5 146)
				(Print 33 4)
				(Bset 46)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance chopUpEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= oldEgoX (ego x?))
				(= oldEgoY (ego y?))
				(User canControl: 0 canInput: 0)
				(theIconBar disable: 0 1 2 3 4 5 6 7)
				(soundFx number: 429 loop: 1 play:)
				(ego setCycle: Walk setMotion: MoveTo 75 124 self)
			)
			(1 (ego setHeading: 270 self))
			(2
				(ego
					view: 23
					setLoop: 1
					cel: 0
					cycleSpeed: 2
					setCycle: End self
				)
			)
			(3 (= cycles 30))
			(4
				(ego setLoop: 2 cel: 0 setCycle: CT 1 1 self)
			)
			(5
				(ego setCycle: End self)
				(soundFx number: 432 loop: 1 play:)
			)
			(6
				(soundFx stop:)
				(= cycles 10)
			)
			(7
				(self setScript: instantReplay self)
			)
			(8 (= seconds 3))
			(9 (EgoDead 935 0 0 33 5))
		)
	)
)

(instance instantReplay of DScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 40) (= local1 0))
			(1
				(= local2 1)
				(theMusic number: 472 loop: -1 play:)
				(viewBox init: stopUpd:)
				(heads init: stopUpd:)
				(mouth1 init:)
				(mouth2 init:)
				(= cycles 1)
			)
			(2
				(mouth1 setCycle: RandCycle 16)
				(Print 33 6 #dispose)
				(= seconds 5)
			)
			(3
				(mouth2 setCycle: RandCycle 18)
				(Print 33 7 #dispose)
				(= seconds 7)
			)
			(4
				(= cycles 15)
				(modelessDialog dispose:)
			)
			(5
				(self
					save1:
						(Display
							33
							8
							dsALIGN
							1
							dsFONT
							0
							dsWIDTH
							165
							dsCOORD
							78
							40
							dsCOLOR
							colLYellow
							dsSAVEPIXELS
						)
				)
				(= cycles 10)
			)
			(6
				(self restore:)
				(= cycles 1)
			)
			(7
				(self
					save1:
						(Display
							33
							8
							dsALIGN
							1
							dsFONT
							0
							dsWIDTH
							165
							dsCOORD
							78
							40
							dsCOLOR
							colLRed
							dsSAVEPIXELS
						)
				)
				(++ local1)
				(= cycles 10)
			)
			(8
				(self restore:)
				(= cycles 4)
				(if (< local1 10) (= state 5))
			)
			(9 (= cycles 10))
			(10
				(ego cycleSpeed: 4 setCycle: Beg self)
			)
			(11
				(ego setLoop: 1 cel: 4 setCycle: Beg self)
			)
			(12 (= seconds 3))
			(13
				(ego
					view: 23
					setLoop: 1
					cel: 0
					cycleSpeed: 2
					setCycle: End self
				)
			)
			(14 (= cycles 20))
			(15
				(ego setLoop: 2 cel: 0 setCycle: CT 1 1 self)
			)
			(16
				(ego setCycle: End self)
				(soundFx number: 432 loop: 1 play:)
			)
			(17
				(soundFx stop:)
				(= seconds 2)
			)
			(18
				(mouth2 setCycle: RandCycle 15)
				(Print 33 9 #dispose)
				(= seconds 5)
			)
			(19
				(modelessDialog dispose:)
				(= cycles 20)
			)
			(20 (self dispose:))
		)
	)
	
	(method (restore)
		(super restore: &rest)
		(= local0 0)
	)
)

(instance laser of Prop
	(properties
		x 49
		y 155
		view 133
		loop 5
		cel 7
		signal $4000
	)
	
	(method (doit)
		(super doit: &rest)
		(Palette 6 56 64 -1)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(2 (Print 33 10))
			(3 (Print 33 11))
			(12 (Print 33 12))
			(11 (Print 33 13))
			(4
				(switch theItem
					(6
						(self setScript: egoBlowItUp)
					)
					(else  (Print 33 14))
				)
			)
			(5 (Print 33 15))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance laserThing1 of Feature
	(properties
		x 46
		y 164
		description {laser thing1}
		onMeCheck $0008
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2
				(Printf
					{There are two metal units here which %s emitting beams of energy.}
					(if (Btst 46) {are no longer} else {appear to be})
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance laserThing2 of Feature
	(properties
		x 109
		y 134
		z 12
		description {laser thing2}
		onMeCheck $0020
		lookStr {}
	)
	
	(method (doVerb theVerb)
		(laserThing1 doVerb: theVerb &rest)
	)
)

(instance viewBox of View
	(properties
		x 227
		y 16
		view 946
		loop 3
		priority 12
		signal $4010
	)
)

(instance heads of View
	(properties
		x 226
		y 8
		view 946
		priority 13
		signal $4010
	)
)

(instance mouth1 of Prop
	(properties
		x 250
		y 36
		view 946
		loop 1
		priority 14
		signal $4010
		cycleSpeed 1
	)
)

(instance mouth2 of Prop
	(properties
		x 271
		y 39
		view 946
		loop 2
		cel 2
		priority 14
		signal $4010
		cycleSpeed 1
	)
)
