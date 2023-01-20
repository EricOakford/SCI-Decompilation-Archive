;;; Sierra Script 1.0 - (do not remove this comment)
(script# 41)
(include sci.sh)
(use Main)
(use Intrface)
(use SQRoom)
(use Talker)
(use Osc)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm41 0
)

(local
	[skimmerPts 16] = [92 156 133 155 141 162 139 174 119 184 98 188 67 179 50 166]
	skimmerStolen
	sellSkimmerTimer
)
(instance rm41 of SQRoom
	(properties
		lookStr {Ulence Flats is a typical example of some of the frontier settlements that sprang up in the early days of Outer Zone exploration. Unfortunately, this attracted many unsavory quick-buckazoid types. Caution is advised.}
		picture 41
		horizon 138
		north 46
		east 42
		west 40
	)
	
	(method (init)
		(self
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						319
						170
						288
						169
						271
						175
						203
						171
						208
						161
						191
						160
						182
						167
						162
						163
						165
						160
						211
						122
						149
						115
						163
						155
						153
						161
						138
						156
						138
						155
						141
						150
						131
						146
						123
						142
						97
						140
						125
						105
						319
						92
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 0 146 65 151 40 172 0 172
					yourself:
				)
		)
		(cond 
			((and (Btst 14) (not (Btst 15))) (Bclr 14) (= skimmerStolen 1))
			((and (Btst 14) (>= enter41 1)) (++ enter41) (= sellSkimmerTimer 100))
		)
		(switch prevRoomNum
			(north
				(= style 10)
				(ego init: posn: 83 (+ horizon 5))
			)
			(east
				(= style 11)
				(ego x: 300)
				(ego init:)
				(HandsOn)
			)
			(west
				(= style 12)
				(ego x: 20)
				(ego init:)
				(HandsOn)
			)
			(43
				(ego init: hide:)
				(if (== (ego view?) 32)
					(Load rsSOUND 403)
					(self setScript: fallDown)
				else
					(if
						(and
							(>= buckazoids 130)
							(not (Btst 63))
							(not (Btst 67))
						)
						(mugger init:)
					)
					(self setScript: comeFromBar)
				)
			)
			(else 
				(= style 8)
				(ego normal: 0 init:)
				(guy init: stopUpd:)
				(theMusic number: 600 loop: -1 flags: 1 play:)
				(Bset 14)
				(SolvePuzzle 25 153)
				(self setScript: getOutaSkimmer)
			)
		)
		(if (Btst 14)
			(skimmerPoly points: @skimmerPts size: 8)
			(self addObstacle: skimmerPoly)
			(skimmer init: stopUpd:)
		)
		(self setRegions: 702)
		(super init:)
		(barSign init: setCycle: Osc)
		(rocketSign init: setCycle: Fwd)
		(radar init: setCycle: Fwd)
		(shuttle init:)
		(pickleShip init:)
		(tallRocket init:)
		(barFront init:)
		(barSigns init:)
		(if (and (Btst 33) (== prevRoomNum 43))
			((ScriptID 702 1) posn: 120 184)
		)
	)
	
	(method (doit)
		(cond 
			(script 0)
			((& (ego onControl: 0) $0002) (self setScript: intoBar))
			(skimmerStolen (Print 41 0) (= skimmerStolen 0))
			(
			(and sellSkimmerTimer (not (-- sellSkimmerTimer)))
				(if (!= (theMusic number?) 600)
					(theMusic number: 600 loop: -1 play: 30 fade: 127 15 10 0)
				)
				(self setScript: sellSkimmerForJet)
			)
		)
		(super doit:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3 (Print 41 1))
			(5 (Print 41 2))
			(12 (Print 41 3))
			(11 (Print 41 4))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance comeFromBar of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego show: posn: 160 158 setMotion: MoveTo 142 166 self)
			)
			(1
				(if (cast contains: mugger)
					(client setScript: muggerPloy)
				else
					(self cue:)
				)
			)
			(2
				(NormalEgo 0 1 61)
				(ego loop: 5 heading: 225)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance intoBar of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 160 161 self)
			)
			(1
				(if (and (Btst 33) (not (Btst 66)))
					(Print 41 5)
					(Bset 66)
				)
				(ego setHeading: 50 self)
			)
			(2
				(ego
					setPri: 11
					xStep: 2
					setLoop: 6
					setMotion: MoveTo 184 174 self
				)
			)
			(3 (curRoom newRoom: 43))
		)
	)
)

(instance fallDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(mugger init: stopUpd:)
				(ego
					show:
					view: 32
					posn: 160 158
					setMotion: MoveTo 142 166 self
				)
			)
			(1
				(ego
					setLoop: 4
					cel: 0
					cycleSpeed: 16
					setCycle: CT 2 1 self
				)
			)
			(2
				(soundFx number: 403 loop: 1 play:)
				(ego setCycle: End self)
			)
			(3 (= seconds 3))
			(4
				(curRoom drawPic: 99 -32762)
				(cast eachElementDo: #hide)
				(= cycles 2)
			)
			(5
				(Print 41 6)
				(= buckazoids 0)
				(ego put: 9 put: 1 put: 10 put: 2 put: 15)
				(mugger dispose:)
				(skimmer dispose:)
				(= seconds 6)
			)
			(6
				(if (Btst 14)
					(EgoDead 945 0 0 41 7)
				else
					(EgoDead 945 0 0 41 8)
				)
			)
		)
	)
)

(instance getOutaSkimmer of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 28
					posn: 122 162
					setLoop: 0
					cel: 0
					setPri: 14
					cycleSpeed: 8
					setCycle: End self
				)
			)
			(1
				(NormalEgo 0 1 61)
				(ego
					posn: 136 185
					heading: 90
					loop: 0
					setMotion: MoveTo 146 181 self
				)
			)
			(2 (= cycles 20))
			(3
				(if (== enter41 0)
					(++ enter41)
					(Print 41 9)
					(Print 41 10)
					(= cycles 20)
				else
					(HandsOn)
					(self dispose:)
				)
			)
			(4
				(Print 41 11)
				(guy
					ignoreActors: 1
					setLoop: 3
					setCycle: Walk
					setMotion: MoveTo 227 215 self
				)
			)
			(5
				(guy setLoop: 1 cel: 3)
				(ego setHeading: 150)
				(= cycles 40)
			)
			(6
				(guy setMotion: MoveTo 126 188 self)
			)
			(7
				(guy setLoop: 6 cel: 0)
				(ego setHeading: 245)
				(= cycles 30)
			)
			(8
				(guy setLoop: 1 setMotion: MoveTo 56 193 self)
			)
			(9
				(guy setLoop: 4 cel: 0)
				(= cycles 45)
			)
			(10
				(guy setMotion: MoveTo 68 185 self)
			)
			(11
				(guy setLoop: 2 setMotion: MoveTo 96 189 self)
			)
			(12
				(guy setLoop: 0 setMotion: MoveTo 182 181 self)
			)
			(13
				(guy setLoop: 2 cel: 5)
				(= cycles 16)
			)
			(14
				(guy ignoreActors: 0 setLoop: 6 cel: 0)
				(ego setHeading: 90)
				(= cycles 22)
			)
			(15
				(guyTalker init: guyBust guyEye guyMouth 141 0 0 0 self)
			)
			(16
				(if modelessDialog (modelessDialog dispose:))
				(if
					(Print
						41
						12
						#at
						12
						-1
						#button
						{You've got a deal!}
						1
						#button
						{No way!}
						0
					)
					(client setScript: sellSkimmerForMoney)
				else
					(guyTalker x: 70 say: 141 1 0 1 self)
				)
			)
			(17
				(guy setLoop: 0 setMotion: MoveTo 340 188 self)
			)
			(18
				(guy dispose:)
				(theMusic client: self fade:)
			)
			(19
				(theMusic number: 601 loop: -1 flags: 1 play:)
				(= sellSkimmerTimer 400)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sellSkimmerForMoney of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(guy setLoop: 6 cel: 0 setCycle: End self)
			)
			(1
				(ego
					view: 28
					setLoop: 4
					cel: 0
					cycleSpeed: 14
					setCycle: CT 1 1 self
				)
			)
			(2
				(if (ego has: 9)
					(guyTalker say: 141 2 0 1 self)
				else
					(guyTalker say: 141 3 0 1 self)
				)
			)
			(3
				(= buckazoids (+ buckazoids 25))
				(ego put: 9)
				(Bset 15)
				(Bclr 14)
				(ego setCycle: Beg self)
			)
			(4
				(NormalEgo 0 1 61)
				(ego loop: 0)
				(guy setCycle: Beg self)
			)
			(5
				(guy
					ignoreActors: 1
					setLoop: 1
					setCycle: Walk
					setMotion: MoveTo 136 185 self
				)
			)
			(6
				(guy
					setPri: 14
					posn: 122 162
					setLoop: 8
					cel: 0
					cycleSpeed: 8
					setCycle: End self
				)
			)
			(7
				(skimmer dispose:)
				((curRoom obstacles?) delete: skimmerPoly)
				(guy
					setLoop: 9
					cel: 0
					cycleSpeed: 12
					moveSpeed: 4
					setCycle: End self
				)
			)
			(8
				(theMusic2 number: 439 loop: -1 play: hold: 1)
				(guy setStep: 10 8 setMotion: MoveTo 170 202 self)
			)
			(9
				(theMusic number: 601 loop: -1 play:)
				(theMusic2 fade: hold: 0)
				(guy dispose:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sellSkimmerForJet of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(guy
					init:
					ignoreActors: 1
					ignoreHorizon: 1
					posn: 144 134
					setLoop: 1
					setCycle: Walk
					setStep: 4 2
					setMotion: MoveTo 104 133 self
				)
			)
			(1
				(guy setLoop: 7 cel: 0)
				(= seconds 2)
			)
			(2
				(guyEye loop: 5 nsLeft: 35 nsTop: 29)
				(guyMouth loop: 3 nsLeft: 37 nsTop: 40)
				(guyTalker
					loop: 1
					posn: 108 25
					nsLeft: 8
					nsTop: 6
					init: guyBust guyEye guyMouth 141 4 0 0 self
				)
			)
			(3
				(guyTalker say: 141 5 0 0 self)
			)
			(4
				(if modelessDialog (modelessDialog dispose:))
				(if
					(Print
						41
						13
						#at
						113
						14
						#button
						{It's a deal!}
						1
						#button
						{No way!}
						0
					)
					(= ticks 4)
				else
					(client setScript: noTwice)
				)
			)
			(5
				(guyTalker say: {"Great!"} 0 0 1 self)
			)
			(6
				(ego setMotion: PolyPath 64 133 self)
			)
			(7 (ego setHeading: 90 self))
			(8
				(guy
					cycleSpeed: 6
					moveSpeed: 6
					setLoop: 6
					cel: 0
					setCycle: End self
				)
			)
			(9
				(ego
					view: 28
					setLoop: 4
					cel: 0
					cycleSpeed: 15
					setCycle: End self
				)
			)
			(10 (guy setCycle: Beg self))
			(11
				(guyMouth nsLeft: 26 nsTop: 40 loop: 2)
				(guyEye nsLeft: 39 nsTop: 29 loop: 4)
				(if (ego has: 9)
					(guyTalker
						loop: 0
						init: guyBust guyEye guyMouth 141 7 0 0 self
					)
				else
					(guyTalker
						loop: 0
						init: guyBust guyEye guyMouth 141 8 0 0 self
					)
				)
			)
			(12
				(guyTalker say: 141 9 0 1 self)
			)
			(13
				(guy
					cycleSpeed: 6
					moveSpeed: 6
					setLoop: 6
					cel: 0
					setCycle: End self
				)
			)
			(14
				(ego
					view: 28
					setLoop: 3
					cel: 0
					cycleSpeed: 15
					setCycle: End self
				)
			)
			(15 (guy setCycle: Beg self))
			(16
				(NormalEgo 0 1 61)
				(ego cycleSpeed: 8 loop: 0 get: 11 get: 18 get: 17 put: 9)
				(SolvePuzzle 5 154)
				(= buckazoids (+ buckazoids 30))
				(Bset 15)
				(Bclr 14)
				(guy
					cycleSpeed: 8
					ignoreActors: 1
					setLoop: 2
					setCycle: Walk
					posn: 103 139
					setMotion: MoveTo 147 174 self
				)
			)
			(17
				(ego setMotion: MoveTo 68 140)
				(guy setLoop: 3 setMotion: MoveTo 137 187 self)
			)
			(18
				(guy
					setPri: 14
					posn: 122 162
					setLoop: 8
					cel: 0
					cycleSpeed: 8
					setCycle: End self
				)
			)
			(19
				(skimmer dispose:)
				((curRoom obstacles?) delete: skimmerPoly)
				(theMusic2 number: 439 loop: -1 play: hold: 1)
				(guy setLoop: 9 cel: 0 cycleSpeed: 10 setCycle: End self)
			)
			(20
				(guy
					setStep: 10 8
					moveSpeed: 2
					setMotion: MoveTo 170 202 self
				)
			)
			(21
				(theMusic number: 601 loop: -1 play:)
				(theMusic2 fade: hold: 0)
				(guy dispose:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance noTwice of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(guyTalker say: 141 6 0 1 self)
			)
			(1
				(guy setLoop: 0 setMotion: MoveTo 164 134 self)
			)
			(2
				(HandsOn)
				(guy dispose:)
				(self dispose:)
			)
		)
	)
)

(instance getOnSkimmer of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 28
					posn: 122 162
					setLoop: 0
					cel: 5
					setPri: 14
					cycleSpeed: 8
					setCycle: Beg self
				)
			)
			(1 (self dispose:))
		)
	)
)

(instance bendOverSkimmer of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					setMotion: PolyPath (skimmer approachX?) (skimmer approachY?) self
				)
			)
			(1
				(ego view: 28 loop: 1 cel: 0 setCycle: Osc 1 self)
			)
			(2
				(if register (Print 41 14) else (Print 41 15))
				(NormalEgo 0 1 61)
				(ego loop: 1)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance muggerPloy of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset 40)
				(ego setMotion: MoveTo 143 180)
				(mugger
					setLoop: 1
					setCycle: Walk
					setMotion: MoveTo 256 184 self
				)
			)
			(1
				(mugger setLoop: 1 setMotion: MoveTo 204 184 self)
			)
			(2
				(mugger setLoop: 8 cel: 0)
				(ego setHeading: 45 self)
			)
			(3 (= seconds 2))
			(4
				(muggerTalker
					init: muggerBust muggerEyes muggerMouth 141 10 0 0 self
				)
			)
			(5
				(muggerTalker say: 141 11 0 1 self)
			)
			(6
				(mugger setCycle: Osc 2 self)
			)
			(7
				(mugger setLoop: 2 cel: 0)
				(= cycles 3)
			)
			(8
				(mugger
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo 339 180 self
				)
			)
			(9
				(mugger dispose:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance guy of Actor
	(properties
		x 289
		y 170
		view 431
		loop 3
		cel 3
		cycleSpeed 6
		xStep 4
		moveSpeed 6
	)
)

(instance mugger of Actor
	(properties
		x 286
		y 174
		description {alien wolf-creature}
		lookStr {This being is about the size of a humanoid but has some odd features. He seems to have a friendly-looking mug.}
		view 432
		loop 3
		cycleSpeed 6
		xStep 4
		moveSpeed 6
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3 (Print 41 16))
			(5 (Print 41 17))
			(12 (Print 41 18))
			(11 (Print 41 18))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance rocketSign of Prop
	(properties
		x 181
		y 66
		description {rocket sign}
		view 141
		priority 10
		signal $0010
		cycleSpeed 2
		detailLevel 3
	)
	
	(method (doVerb)
		(barSigns doVerb: &rest)
	)
)

(instance barSign of Prop
	(properties
		x 174
		y 110
		description {rocket sign}
		view 141
		loop 1
		priority 10
		signal $0010
		cycleSpeed 4
		detailLevel 2
	)
	
	(method (doVerb)
		(barSigns doVerb: &rest)
	)
)

(instance skimmer of View
	(properties
		x 122
		y 162
		description {sand skimmer}
		approachX 132
		approachY 180
		view 141
		loop 2
		priority 13
		signal $4010
	)
	
	(method (init)
		(self approachVerbs: 3 2)
		(super init: &rest)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(3
				(if (Btst 15)
					(Print 41 19)
				else
					(ego get: 9)
					(Bset 15)
					(curRoom setScript: bendOverSkimmer 0 0)
				)
			)
			(2
				(if (Btst 15) (Print 41 20) else (Print 41 21))
			)
			(4
				(switch theItem
					(9
						(curRoom setScript: bendOverSkimmer 0 1)
					)
					(else 
						(super doVerb: theVerb theItem)
					)
				)
			)
			(5 (Print 41 22))
			(12 (Print 41 23))
			(11 (Print 41 24))
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance radar of Prop
	(properties
		x 83
		y 164
		description {force field sensor}
		lookStr {The settlement of Ulence Flats is surrounded by these force field generators. They repel undesirables, such as the Grell, which thrive below the sands. It will allow only airborne vehicles in or out.}
		view 141
		loop 3
		priority 15
		signal $4010
		cycleSpeed 4
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(3 (Print 41 25))
			(5 (Print 41 26))
			(12 (Print 41 25))
			(11 (Print 41 25))
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance shuttle of Feature
	(properties
		description {shuttle craft}
		onMeCheck $0004
		lookStr {This appears to be a long-range shuttle, perhaps from a large starship. It looks vaguely familiar somehow.}
	)
	
	(method (doVerb theVerb)
		(= x ((User curEvent?) x?))
		(= y ((User curEvent?) y?))
		(switch theVerb
			(3 (Print 41 27))
			(5 (Print 41 28))
			(12 (Print 41 29))
			(11 (Print 41 30))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance pickleShip of Feature
	(properties
		description {pickle ship}
		onMeCheck $0010
		lookStr {This ship is a real classic - a WalWood WarpBlaster ZX with the original paint job. You've only seen these on old postcards.}
	)
	
	(method (doVerb theVerb)
		(= x ((User curEvent?) x?))
		(= y ((User curEvent?) y?))
		(switch theVerb
			(3 (Print 41 31))
			(5 (Print 41 32))
			(12 (Print 41 33))
			(11 (Print 41 34))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance tallRocket of Feature
	(properties
		description {tall rocket ship}
		onMeCheck $0008
		lookStr {This one's a genuine antique from the twenty-fourth-and-a-half century.}
	)
	
	(method (doVerb theVerb)
		(= x ((User curEvent?) x?))
		(= y ((User curEvent?) y?))
		(switch theVerb
			(3 (Print 41 35))
			(5 (Print 41 36))
			(12 (Print 41 37))
			(11 (Print 41 38))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance barFront of Feature
	(properties
		description {bar}
		onMeCheck $0020
		lookStr {There is a rounded structure here with a door on one side. It is typical of some of the prefab structures constructed in frontier areas years ago.}
	)
	
	(method (doVerb)
		(= x ((User curEvent?) x?))
		(= y ((User curEvent?) y?))
		(super doVerb: &rest)
	)
)

(instance barSigns of Feature
	(properties
		description {Rocket Bar's sign}
		onMeCheck $0040
	)
	
	(method (doVerb theVerb)
		(= x ((User curEvent?) x?))
		(= y ((User curEvent?) y?))
		(switch theVerb
			(2 (Print 41 39) (Print 41 40))
			(3 (Print 41 41))
			(5 (Print 41 42))
			(12 (Print 41 43))
			(11 (Print 41 44))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance guyTalker of Talker
	(properties
		x 15
		y 75
		nsTop 58
		nsLeft 205
		view 506
	)
)

(instance guyBust of View
	(properties
		view 506
		cel 1
	)
)

(instance guyMouth of Prop
	(properties
		nsTop 40
		nsLeft 26
		view 506
		loop 2
		cycleSpeed 12
	)
)

(instance guyEye of Prop
	(properties
		nsTop 29
		nsLeft 39
		view 506
		loop 4
		cycleSpeed 30
	)
)

(instance muggerTalker of Talker
	(properties
		x 7
		y 46
		nsTop 5
		nsLeft 207
		view 515
	)
)

(instance muggerBust of View
	(properties
		view 515
		cel 1
	)
)

(instance muggerMouth of Prop
	(properties
		nsTop 44
		nsLeft 19
		view 515
		loop 2
		cycleSpeed 8
	)
)

(instance muggerEyes of Prop
	(properties
		nsTop 32
		nsLeft 15
		view 515
		loop 1
		cycleSpeed 16
	)
)

(instance skimmerPoly of Polygon
	(properties
		type $0002
	)
)
