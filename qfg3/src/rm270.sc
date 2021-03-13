;;; Sierra Script 1.0 - (do not remove this comment)
(script# 270)
(include game.sh) (include "270.shm")
(use Main)
(use TellerIcon)
(use ArrayPath)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Grooper)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm270 0
)

(local
	path1 = [-15 156 20 154 40 132 107 121 127 97 114 89 42 97 10 77 PATHEND]
	path2 = [10 77 42 97 114 89 127 97 107 121 40 132 20 154 -15 156 PATHEND]
	path3 = [-15 162 30 156 52 137 118 124 143 102 213 97 270 48 237 36 240 26 330 18 PATHEND]
	path4 = [330 18 240 26 237 36 270 48 213 97 143 102 118 124 52 137 30 156 -15 162 PATHEND]
	path5 = [41 70 71 91 116 87 149 97 220 100 288 51 330 54 PATHEND]
	path6 = [330 54 288 51 220 100 149 97 116 87 71 91 41 70 PATHEND]
	path7 = [44 71 71 90 116 85 330 159 PATHEND]
	path8 = [330 159 116 85 71 90 44 71 PATHEND]
	path9 = [330 150 235 120 230 104 291 56 330 59 PATHEND]
	path10 = [330 59 291 56 230 104 235 120 330 150 PATHEND]
	path11 = [-15 178 45 163 65 142 129 129 159 107 201 107 330 154 PATHEND]
	path12 = [330 154 201 107 159 107 129 129 65 142 45 163 -15 178 PATHEND]
	[peoplePaths 20]
	[local196 10]
	numTownPeople
	maxPeople =  4
	local208
	personView = [270 271 272 271 272]
	personNoun = [N_PERSON1 N_PERSON2 N_PERSON3 N_PERSON4 N_PERSON5]
	toX
	toY
	nearBuilding
)
(instance rm270 of Room
	(properties
		noun N_ROOM
		picture 270
		horizon 70
		north 320
		picAngle 70
		vanishingX 310
		vanishingY -100
	)
	
	(method (init)
		(= local208 1)
		(= [peoplePaths 0] @path1)
		(= [peoplePaths 1] @path12)
		(= [peoplePaths 2] @path3)
		(= [peoplePaths 3] @path11)
		(= [peoplePaths 4] @path7)
		(= [peoplePaths 5] @path5)
		(= [peoplePaths 6] @path7)
		(= [peoplePaths 7] @path3)
		(= [peoplePaths 8] @path7)
		(= [peoplePaths 9] @path10)
		(= [peoplePaths 10] @path2)
		(= [peoplePaths 11] @path9)
		(= [peoplePaths 12] @path4)
		(= [peoplePaths 13] @path12)
		(= [peoplePaths 14] @path3)
		(= [peoplePaths 15] @path6)
		(= [peoplePaths 16] @path8)
		(= [peoplePaths 17] @path6)
		(= [peoplePaths 18] @path8)
		(= [peoplePaths 19] @path9)
		(LoadMany RES_VIEW 270 271 272)
		(ego init: normalize: setScale: Scaler 39 30 135 70)
		(switch prevRoomNum
			(200
				(ego x: 325 y: 153)
				(= toX 312)
				(= toY 153)
				(HandsOff)
				(self setScript: moveOut)
			)
			(210
				(ego x: 325 y: 153)
				(= toX 312)
				(= toY 153)
				(HandsOff)
				(self setScript: moveOut)
			)
			(240
				(ego x: -5 y: 157)
				(= toX 8)
				(= toY 152)
				(HandsOff)
				(self setScript: moveOut)
			)
			(280
				(cSound fade: 70 5 3 0 hold: 1)
				(ego x: 297 y: 127)
				(= toX 277)
				(= toY 132)
				(HandsOff)
				(self setScript: moveOut)
			)
			(285
				(cSound fade: 70 5 3 0 hold: 1)
				(ego x: 297 y: 127)
				(= toX 278)
				(= toY 132)
				(HandsOff)
				(self setScript: moveOut)
			)
			(290
				(cSound fade: 70 5 3 0 hold: 1 holdVal: 1)
				(ego x: 142 y: 76)
				(= toX 132)
				(= toY 83)
				(HandsOff)
				(self setScript: moveOut)
			)
			(300
				(cSound fade: 70 5 3 0 hold: 1)
				(if (< (ego x?) 115)
					(ego x: 56 y: 81)
					(= toX 69)
					(= toY 88)
				else
					(ego x: 22 y: 84)
					(= toX 35)
					(= toY 91)
				)
				(HandsOff)
				(self setScript: moveOut)
			)
			(320
				(ego x: 259 y: 71)
				(HandsOn)
			)
			(else 
				(ego x: 160 y: 160)
				(HandsOn)
			)
		)
		(if (!= prevRoomNum 210)
			(soundFx number: 925 setLoop: -1 play: 100)
		)
		(super init:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						319 145
						286 136
						294 123
						278 134
						255 126
						271 119
						238 108
						302 59
						319 60
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0
						116 0
						263 48
						205 97
						176 88
						148 91
						130 83
						140 72
						124 81
						106 75
						90 75
						94 87
						71 89
						45 72
						65 91
						40 92
						18 78
						30 93
						8 95
						7 101
						116 91
						95 117
						22 126
						0 146
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						0 187
						53 183
						59 170
						82 146
						186 124
						203 126
						95 146
						194 186
						319 164
						319 189
						0 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						201 136
						263 160
						195 173
						135 148
					yourself:
				)
		)
		(if (townPerson1 checkDetail:)
			(++ numTownPeople)
			(townPerson1
				detailLevel: 0
				init:
				setScript: (walkAround new:)
				setScale: Scaler 91 43 190 0
				setLoop: GradualLooper
				setCycle: Walk
			)
		)
		(if (not Night)
			(if (townPerson2 checkDetail:)
				(++ numTownPeople)
				(townPerson2
					detailLevel: 0
					init:
					setScript: (walkAround new:)
					setScale: Scaler 91 43 190 0
					setLoop: GradualLooper
					setCycle: Walk
				)
			)
			(if (townPerson3 checkDetail:)
				(++ numTownPeople)
				(townPerson3
					detailLevel: 0
					init:
					setScript: (walkAround new:)
					setScale: Scaler 91 43 190 0
					setLoop: GradualLooper
					setCycle: Walk
				)
			)
			(if (townPerson4 checkDetail:)
				(++ numTownPeople)
				(townPerson4
					detailLevel: 0
					init:
					setScript: (walkAround new:)
					setScale: Scaler 91 43 190 0
					setLoop: GradualLooper
					setCycle: Walk
				)
			)
		)
		(guard1 init: noun: N_GUARD addToPic:)
		(garden init:)
		(trees init:)
		(magicsign init:)
		(apothsign init:)
		(restsign init:)
		(restentr init:)
		(apothentr init:)
		(magicentr init:)
		(kingentr init:)
		(bazstair init:)
		(upplant init:)
		(rightplant init:)
		(upstair init:)
	)
	
	(method (doit &tmp thisControl)
		(super doit: &rest)
		(cond 
			(
				(and
					(== (= thisControl (ego onControl: origin)) cLBLUE)
					(not nearBuilding)
				)
				(= nearBuilding TRUE)
				(soundFx fade: 1 3 5 0)
				(cSound number: 280 setLoop: -1 play: 1 hold: 1)
				(cSound fade: 70 3 5 0)
			)
			((and (== thisControl cLGREEN) nearBuilding)
				(= nearBuilding FALSE)
				(cSound fade: 0 5 3 1)
				(soundFx fade: 100 3 5 0)
			)
			((and (== thisControl cLCYAN) (not nearBuilding))
				(= nearBuilding 1)
				(soundFx fade: 1 3 5 0)
				(cSound number: 290 setLoop: -1 play: 1 hold: 1)
				(cSound fade: 70 3 5 0)
			)
			((and (== thisControl cLRED) nearBuilding)
				(= nearBuilding 0)
				(cSound fade: 0 5 3 1)
				(soundFx fade: 100 3 5 0)
			)
			((and (== thisControl cLMAGENTA) (not nearBuilding))
				(= nearBuilding 1)
				(soundFx fade: 1 3 5 0)
				(cSound number: 300 setLoop: -1 play: 1 hold: 1)
				(cSound fade: 70 3 5 0)
			)
			((and (== thisControl cYELLOW) nearBuilding)
				(= nearBuilding 0)
				(cSound fade: 0 5 3 1)
				(soundFx fade: 100 3 5 0)
			)
		)
		(cond 
			(script)
			((not (if (< 5 (ego x?)) (< (ego x?) 315)))
				(HandsOff)
				(self setScript: sExit)
			)
			((== thisControl cBLUE)
				(HandsOff)
				(self setScript: toRestLeft)
			)
			((== thisControl cGREEN)
				(HandsOff)
				(self setScript: toRestRight)
			)
			((== thisControl cCYAN)
				(HandsOff)
				(self setScript: toApoth)
			)
			((== thisControl cRED)
				(HandsOff)
				(self setScript: toKreesha)
			)
			((== thisControl cMAGENTA)
				(if (not (& (ego signal?) fixPriOn))
					(ego signal: (| (ego signal?) fixPriOn) priority: 9)
				)
			)
			((& (ego signal?) fixPriOn)
				(ego signal: (& (ego signal?) $ffef))
			)
		)
	)
	
	(method (dispose)
		(UnLoad RES_VIEW 270)
		(UnLoad RES_VIEW 271)
		(UnLoad RES_VIEW 272)
		(super dispose:)
	)
	
	(method (newRoom n)
		(if (== n 320)
			(soundFx fade:)
		)
		(super newRoom: n)
	)
)

(instance sExit of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (<= (ego x?) 5)
					(= register 240)
					(ego setMotion: PolyPath -5 (ego y?) self)
				else
					(= register 210)
					(ego setMotion: PolyPath 325 (ego y?) self)
				)
			)
			(1
				(curRoom newRoom: register)
			)
		)
	)
)

(instance toRestLeft of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cSound fade: 100 5 5 0)
				(ego setMotion: PolyPath 22 84 self)
			)
			(1
				(soundFx fade:)
				(cSound fade: 110 5 5 0)
				(curRoom newRoom: 300)
			)
		)
	)
)

(instance toRestRight of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cSound fade: 100 5 5 0)
				(ego setMotion: PolyPath 56 81 self)
			)
			(1
				(soundFx fade:)
				(cSound fade: 110 5 5 0)
				(curRoom newRoom: 300)
			)
		)
	)
)

(instance toKreesha of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cSound fade: 100 5 5 0)
				(ego setMotion: PolyPath 297 127 self)
			)
			(1
				(soundFx fade:)
				(cSound fade: 127 5 5 0)
				(curRoom newRoom: 280)
			)
		)
	)
)

(instance toApoth of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cSound fade: 127 5 5 0)
				(ego setMotion: PolyPath 142 76 self)
			)
			(1
				(soundFx fade:)
				(curRoom newRoom: 290)
			)
		)
	)
)

(instance walkAround of Script
	
	(method (changeState newState &tmp personSpeed i)
		(switch (= state newState)
			(0
				(if
				(and (< local208 numTownPeople) (not (Random 0 2)))
					(++ local208)
					(= seconds (Random 10 25))
				else
					(self cue:)
				)
			)
			(1
				(= personSpeed (Random 5 12))
				(= i (Random 0 maxPeople))
				(client
					view: [personView i]
					noun: [personNoun i]
					moveSpeed: personSpeed
					cycleSpeed: personSpeed
				)
				(= register (Random 0 9))
				(if (== (client view?) 270)
					(while (OneOf [peoplePaths register] @path1 @path5 @path7)
						(= register (Random 0 9))
					)
				else
					(while [local196 register]
						(= register (Random 0 9))
					)
				)
				(= [local196 register] 1)
				(if (Random 0 1) (= register (+ register 10)))
				(if (< local208 numTownPeople)
					(++ local208)
					(client
						x: (WordAt [peoplePaths register] 2)
						y: (WordAt [peoplePaths register] 3)
						setMotion: ArrayPath [peoplePaths register] 2 self
					)
				else
					(client
						x: (WordAt [peoplePaths register] 0)
						y: (WordAt [peoplePaths register] 1)
						setMotion: ArrayPath [peoplePaths register] 0 self
					)
				)
			)
			(2
				(if (> register 9)
					(-= register 10)
				)
				(= [local196 register] 0)
				(client
					x: (- 0 (Random 200 500))
					y: (- 0 (Random 200 500))
				)
				(= seconds (Random 5 35))
			)
			(3
				(self init:)
			)
		)
	)
)

(instance moveOut of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath toX toY self)
				(= seconds 3)
			)
			(1
				(if (and (!= (ego x?) toX) (!= (ego y?) toY))
					(self init:)
				)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance townPerson1 of Actor
	(properties
		view 270
		signal (| ignrAct ignrHrz)
		detailLevel 5
	)
	
	(method (doVerb theVerb)
		(if (or (== theVerb V_LOOK) (== theVerb V_TALK))
			(super doVerb: theVerb &rest)
		else
			(Teller doVerb: theVerb &rest)
		)
	)
	
	(method (checkDetail)
		(return
			(if (< (theGame detailLevel:) detailLevel)
				(return FALSE)
			else
				(return TRUE)
			)
		)
	)
)

(instance townPerson2 of Actor
	(properties
		view 270
		signal (| ignrAct ignrHrz)
		detailLevel 4
	)
	
	(method (doVerb theVerb)
		(if (or (== theVerb V_LOOK) (== theVerb V_TALK))
			(super doVerb: theVerb &rest)
		else
			(Teller doVerb: theVerb &rest)
		)
	)
	
	(method (checkDetail)
		(return
			(if (< (theGame detailLevel:) detailLevel)
				(return FALSE)
			else
				(return TRUE)
			)
		)
	)
)

(instance townPerson3 of Actor
	(properties
		view 270
		signal (| ignrAct ignrHrz)
		detailLevel 3
	)
	
	(method (doVerb theVerb)
		(if (or (== theVerb V_LOOK) (== theVerb V_TALK))
			(super doVerb: theVerb &rest)
		else
			(Teller doVerb: theVerb &rest)
		)
	)
	
	(method (checkDetail)
		(return
			(if (< (theGame detailLevel:) detailLevel)
				(return FALSE)
			else
				(return TRUE)
			)
		)
	)
)

(instance townPerson4 of Actor
	(properties
		view 270
		signal (| ignrAct ignrHrz)
		detailLevel 2
	)
	
	(method (doVerb theVerb)
		(if (or (== theVerb V_LOOK) (== theVerb V_TALK))
			(super doVerb: theVerb &rest)
		else
			(Teller doVerb: theVerb &rest)
		)
	)
	
	(method (checkDetail)
		(return
			(if (< (theGame detailLevel:) detailLevel)
				(return FALSE)
			else
				(return TRUE)
			)
		)
	)
)

(instance guard1 of View
	(properties
		x 273
		y 43
		view 197
		loop 1
		signal ignrAct
	)
	
	(method (doVerb theVerb)
		(if (or (== theVerb V_LOOK) (== theVerb V_TALK))
			(super doVerb: theVerb &rest)
		else
			(Teller doVerb: theVerb &rest)
		)
	)
)

(instance garden of Feature
	(properties
		x 198
		y 150
		noun N_GARDEN
		nsTop 136
		nsLeft 156
		nsBottom 165
		nsRight 240
		sightAngle 180
	)
)

(instance trees of Feature
	(properties
		x 191
		y 118
		noun N_TREES
		nsTop 102
		nsLeft 152
		nsBottom 135
		nsRight 230
		sightAngle 180
	)
)

(instance magicsign of Feature
	(properties
		x 285
		y 101
		noun N_MAGIC_SIGN
		nsTop 87
		nsLeft 269
		nsBottom 116
		nsRight 302
		sightAngle 180
	)
)

(instance apothsign of Feature
	(properties
		x 128
		y 55
		noun N_APOTHECARY_SIGN
		nsTop 45
		nsLeft 116
		nsBottom 65
		nsRight 140
		sightAngle 180
	)
)

(instance restsign of Feature
	(properties
		x 48
		y 63
		noun N_INN_SIGN
		nsTop 59
		nsLeft 21
		nsBottom 68
		nsRight 76
		sightAngle 180
	)
)

(instance restentr of Feature
	(properties
		x 48
		y 78
		noun N_INN_DOOR
		nsTop 69
		nsLeft 22
		nsBottom 88
		nsRight 75
		sightAngle 180
	)
)

(instance apothentr of Feature
	(properties
		x 128
		y 73
		noun N_APOTHECARY_DOOR
		nsTop 65
		nsLeft 117
		nsBottom 82
		nsRight 140
		sightAngle 180
	)
)

(instance magicentr of Feature
	(properties
		x 285
		y 125
		noun N_KREESHA_DOOR
		nsTop 114
		nsLeft 270
		nsBottom 137
		nsRight 300
		sightAngle 180
	)
)

(instance kingentr of Feature
	(properties
		x 302
		y 24
		noun N_KING_DOOR
		nsTop 10
		nsLeft 289
		nsBottom 39
		nsRight 315
		sightAngle 180
	)
)

(instance bazstair of Feature
	(properties
		x 33
		y 145
		noun N_BAZAAR_STAIRS
		nsTop 121
		nsBottom 170
		nsRight 66
		sightAngle 180
	)
)

(instance upplant of Feature
	(properties
		x 152
		y 35
		noun N_UP_PLANT
		nsTop 28
		nsLeft 135
		nsBottom 42
		nsRight 170
		sightAngle 180
	)
)

(instance rightplant of Feature
	(properties
		x 305
		y 79
		noun N_RIGHT_PLANT
		nsTop 71
		nsLeft 291
		nsBottom 87
		nsRight 319
		sightAngle 180
	)
)

(instance upstair of Feature
	(properties
		x 256
		y 69
		noun N_KING_STAIRS
		nsTop 52
		nsLeft 234
		nsBottom 87
		nsRight 279
		sightAngle 180
	)
)
