;;; Sierra Script 1.0 - (do not remove this comment)
(script# 780)
(include game.sh) (include "780.shm")
(use Main)
(use TellerIcon)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm780 0
)

(local
	local0
	local1
)
(instance rm780 of Room
	(properties
		noun N_ROOM
		picture 780
	)
	
	(method (init)
		(ego setScale: 190 x: 5 y: 76 init: normalize:)
		(HandsOn)
		(theIconBar disable: 6)
		(super init:)
		(if Night
			(globalSound number: 927 setLoop: -1 play: 127)
		else
			(globalSound number: 914 setLoop: -1 play: 127)
		)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init: 170 120 170 123 204 123 204 120 173 120
					yourself:
				)
				((Polygon new:)
					type: 3
					init:
						168
						111
						129
						126
						48
						126
						48
						139
						147
						156
						213
						146
						230
						137
						143
						143
						129
						147
						55
						136
						55
						130
						132
						130
						187
						109
						195
						104
						124
						104
						0
						73
						0
						92
						137
						111
					yourself:
				)
		)
		(egoTell init: ego)
		(platform init:)
		(stream init:)
		(purpleLeaves init:)
		(purpleTree init:)
		(purpleBranch init:)
		(orangeTree init:)
		(fungi init:)
		(flower init:)
		(if (> (theGame detailLevel:) 2)
			(water1 setCycle: Forward init:)
			(water2 setCycle: Forward init:)
		)
		(walkHandler addToFront: platform)
		(curRoom setScript: egoEnters)
	)
	
	(method (dispose)
		(walkHandler delete: platform)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(81 (messager say: 1 6 3))
			(83 (messager say: 1 6 3))
			(20 (messager say: 1 6 3))
			(88 (messager say: 1 6 3))
			(33 (messager say: 1 6 3))
			(78 (messager say: 1 6 3))
			(76 (messager say: 1 6 3))
			(84 (messager say: 1 6 3))
			(80 (messager say: 1 6 3))
			(86 (messager say: 1 6 3))
			(74
				(if (== local0 0)
					(self setScript: goToBed)
				else
					(messager say: 1 6 4)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance goToBed of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 147 148 self)
			)
			(1
				(ego view: 35 loop: 0 cel: 0 x: 166 setCycle: EndLoop self)
			)
			(2
				(if (= temp0 (PalVary PALVARYINFO))
					(if (< temp0 64)
						(PalVary PALVARYNEWTIME 3)
						(= seconds 5)
					else
						(self cue:)
					)
				else
					(PalVary PALVARYSTART 310 3)
					(Btst 81)
					(= seconds 15)
				)
			)
			(3
				(PalVary PALVARYREVERSE 3)
				(Bclr 81)
				(= seconds 4)
			)
			(4
				((ScriptID 7 7) init: 5 40)
				(ego setCycle: BegLoop self)
			)
			(5
				(ego
					normalize: 6
					cel: 6
					x: 147
					changeGait: 0
					code: outChek
				)
				(HandsOn)
				(theIconBar disable: 6)
				(self dispose:)
			)
		)
	)
)

(instance climbDownPlatform of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (== (cSound number?) 782)
					(cSound number: 780 setLoop: -1 play: 127)
				)
				(ego setMotion: MoveTo 161 124 self)
			)
			(1
				(ego setMotion: MoveTo 194 133 self)
			)
			(2
				(ego setMotion: MoveTo 172 137 self)
			)
			(3
				(ego setLoop: 3 setMotion: MoveTo 163 155 self)
			)
			(4
				(ego
					setLoop: -1
					code: outChek
					setMotion: MoveTo 139 146 self
				)
			)
			(5
				(ego setPri: -1 setScale: 190)
				(HandsOn)
				(theIconBar disable: 6)
				(= local0 0)
				(self dispose:)
			)
		)
	)
)

(instance climbOntoPlatform of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 139 146 self)
			)
			(1
				(ego setScale: setPri: 12 setMotion: MoveTo 163 155 self)
			)
			(2
				(ego setPri: 12 setLoop: 2 setMotion: MoveTo 172 137 self)
			)
			(3
				(ego setLoop: -1 setMotion: MoveTo 194 133 self)
			)
			(4
				(ego setMotion: MoveTo 161 124 self)
			)
			(5
				(ego setMotion: MoveTo 187 122 self)
			)
			(6
				(ego code: downChek setHeading: 180)
				(HandsOn)
				(theIconBar disable: 6)
				(= local0 1)
				(self dispose:)
			)
		)
	)
)

(instance egoEnters of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= [egoStats 18] (ego maxMana:))
				(= [egoStats 16] (ego maxHealth:))
				(= [egoStats 17] (ego maxStamina:))
				(cSound fade: self)
			)
			(1
				(cSound number: 780 setLoop: -1 play: 127)
				(messager say: 1 6 1 0 self)
			)
			(2
				(ego setMotion: PolyPath 147 148 self)
			)
			(3
				(ego code: outChek setHeading: 180)
				(HandsOn)
				(theIconBar disable: 6)
				(self dispose:)
			)
		)
	)
)

(instance giveItem of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== local0 0)
					(self setScript: climbOntoPlatform self)
				else
					(= cycles 1)
				)
			)
			(1
				(HandsOff)
				(ego view: 4 loop: 0 setCycle: EndLoop self)
			)
			(2
				(if (not local1) (messager say: 1 6 8))
				(ego setCycle: BegLoop self)
				(cSound number: 783 setLoop: 1 play: 127 self)
			)
			(3)
			(4
				(cSound number: 783 setLoop: 1 play: 127 self)
				(if local1
					(thatOldFruit init: setPri: 12)
					(ego drop: 37 1 get: 15)
					(messager say: 1 6 7 0 self)
				else
					(ego normalize: setPri: 12 setHeading: 270)
					(thatOldStaff init: cycleSpeed: 7 setCycle: EndLoop self)
				)
			)
			(5)
			(6
				(cSound number: 782 setLoop: -1 play: 127)
				(ego normalize: setPri: 12 setMotion: MoveTo 178 122 self)
			)
			(7
				(ego view: 31 loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(8
				(if local1
					(thatOldFruit dispose:)
					(ego setCycle: BegLoop self)
				else
					(thatOldStaff dispose:)
					(ego setCycle: BegLoop self)
				)
			)
			(9
				(HandsOn)
				(if local1
					(ego get: 38 solvePuzzle: 327 5 normalize: setPri: 12)
					(Bset 157)
				else
					(ego get: 44 solvePuzzle: 328 5 2 normalize: setPri: 12)
					(ego drop: 39)
					(messager say: 1 6 9)
				)
				(theIconBar disable: 6)
				(self dispose:)
			)
		)
	)
)

(instance thatOldFruit of Prop
	(properties
		x 177
		y 75
		view 790
		signal $4000
	)
)

(instance thatOldStaff of Prop
	(properties
		x 143
		y 83
		view 781
		loop 1
		signal $4000
	)
)

(instance stepFeat of Feature
	(properties
		x 180
		y 141
		nsTop 121
		nsLeft 147
		nsBottom 161
		nsRight 213
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (== local0 0)
					(curRoom setScript: climbOntoPlatform)
				else
					(curRoom setScript: climbDownPlatform)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance water1 of Prop
	(properties
		x 203
		y 189
		view 780
		priority 14
		signal $4010
		detailLevel 3
	)
)

(instance water2 of Prop
	(properties
		x 245
		y 189
		view 780
		loop 1
		priority 14
		signal $4010
		detailLevel 3
	)
)

(instance egoTell of Teller
	(properties)
	
	(method (doVerb theVerb)
		(if local0
			(platform doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance sFx of GlorySong
	(properties)
)

(instance platform of Feature
	(properties
		x 186
		y 119
		z 1
		noun 8
		nsTop 119
		nsLeft 150
		nsBottom 165
		nsRight 210
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(50
				(if ((inventory at: 39) state?)
					(= local1 0)
					(Bset 139)
					(if (not (Btst 139)) (ego addHonor: 20))
					(curRoom setScript: giveItem)
				else
					(messager say: 1 6 5)
				)
			)
			(48
				(= local1 1)
				(if (not (Btst 138))
					(ego addHonor: 20)
					(Bset 138)
					(curRoom setScript: giveItem)
				else
					(messager say: 1 6 6)
				)
			)
			(4
				(if (== local0 0)
					(curRoom setScript: climbOntoPlatform)
				else
					(curRoom setScript: climbDownPlatform)
				)
			)
			(3
				(if (== local0 0)
					(curRoom setScript: climbOntoPlatform)
				else
					(curRoom setScript: climbDownPlatform)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance stream of Feature
	(properties
		x 231
		y 173
		noun 2
		nsTop 157
		nsLeft 165
		nsBottom 189
		nsRight 298
		sightAngle 180
	)
)

(instance purpleLeaves of Feature
	(properties
		x 249
		y 21
		noun 3
		nsTop -2
		nsLeft 180
		nsBottom 45
		nsRight 319
		sightAngle 180
	)
)

(instance purpleTree of Feature
	(properties
		x 259
		y 95
		noun 9
		nsTop 48
		nsLeft 223
		nsBottom 142
		nsRight 295
		sightAngle 180
	)
)

(instance purpleBranch of Feature
	(properties
		x 177
		y 72
		noun 4
		nsTop 48
		nsLeft 132
		nsBottom 97
		nsRight 222
		sightAngle 180
	)
)

(instance orangeTree of Feature
	(properties
		x 99
		y 71
		noun 5
		nsTop 42
		nsLeft 71
		nsBottom 101
		nsRight 127
		sightAngle 180
	)
)

(instance fungi of Feature
	(properties
		x 82
		y 169
		noun 6
		nsTop 150
		nsLeft 38
		nsBottom 189
		nsRight 127
		sightAngle 180
	)
)

(instance flower of Feature
	(properties
		x 301
		y 147
		noun 7
		nsTop 138
		nsLeft 288
		nsBottom 157
		nsRight 315
		sightAngle 180
	)
)

(instance outChek of Code
	(properties)
	
	(method (doit)
		(if (ego inRect: 0 50 50 95) (curRoom newRoom: 760))
	)
)

(instance downChek of Code
	(properties)
	
	(method (doit)
		(if (< (ego x?) 174)
			(ego code: outChek)
			(curRoom setScript: climbDownPlatform)
		)
	)
)
