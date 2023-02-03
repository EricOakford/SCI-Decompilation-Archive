;;; Sierra Script 1.0 - (do not remove this comment)
(script# 320)
(include sci.sh)
(use Main)
(use Scaler)
(use Polygon)
(use Feature)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm320 0
)

(local
	local0
	local1
	local2 =  13
	[local3 14] = [129 127 128 129 133 137 143 154 167 172 175 173 173 174]
	[local17 14] = [140 140 140 140 140 140 143 144 153 175 187 185 185 187]
	[local31 14] = [0 1 2 3 4 5 6 6 6 6 7 8 9 10]
	[local45 14] = [195 197 196 195 191 187 181 171 165 156 151 151 150 149]
	[local59 14] = [142 142 142 142 142 142 143 141 150 168 189 184 184 185]
	local73
)
(instance rm320 of Rm
	(properties
		noun 7
		picture 63
		style $800a
		vanishingY 80
	)
	
	(method (init)
		(self setRegions: 350)
		(LoadMany 128 430 436)
		(NormalEgo 0)
		(ego
			actions: (ScriptID 350 1)
			setScale: Scaler 79 26 145 59
		)
		(switch prevRoomNum
			(305
				(= style 12)
				(curRoom setScript: sUseTunnel 0 1)
			)
			(325
				(= style -32758)
				(ego setHeading: 180 x: 288 y: 153 edgeHit: 0 init:)
				(theGame handsOn:)
			)
			(else 
				(= style -32758)
				(curRoom setScript: sWD40ShipLands)
			)
		)
		(rogRock init:)
		(chasm init: setOnMeCheck: 1 2)
		(mountains init: setOnMeCheck: 1 4)
		(path init: setOnMeCheck: 1 8)
		(ship init: setOnMeCheck: 1 16)
		(wall init: setOnMeCheck: 1 32)
		(cave init: setOnMeCheck: 1 64)
		(ledge init: setOnMeCheck: 1 512)
		(sky init: setOnMeCheck: 1 1024)
		(if (Btst 212)
			(leg1 init:)
			(leg2 init:)
			(fire1 init: setCycle: Fwd)
			(fire2 init: setCycle: Fwd)
			(heat1 init: setCycle: Fwd)
			(heat2 init: setCycle: Fwd)
			(curRoom
				addObstacle:
					((Polygon new:)
						type: 3
						init:
							70
							105
							64
							134
							88
							144
							124
							144
							136
							140
							114
							136
							119
							133
							92
							125
							110
							117
							89
							112
							96
							110
							71
							104
						yourself:
					)
			)
		else
			(curRoom
				addObstacle:
					((Polygon new:)
						type: 3
						init:
							192
							146
							219
							154
							296
							154
							316
							145
							309
							132
							287
							126
							263
							119
							250
							123
							274
							126
							303
							134
							304
							141
							285
							150
							237
							150
							199
							140
						yourself:
					)
					((Polygon new:)
						type: 3
						init:
							70
							105
							64
							134
							88
							144
							124
							144
							136
							140
							114
							136
							119
							133
							92
							125
							110
							117
							89
							112
							96
							110
							71
							104
						yourself:
					)
			)
		)
		(super init:)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(
				(and
					(SteppedOn ego 64)
					(not (curRoom script?))
				)
				(curRoom setScript: sUseTunnel 0 0)
			)
			((SteppedOn ego 128) (curRoom newRoom: 325))
			(
				(and
					(InRect 130 135 140 145 ego)
					(< 0 (ego heading?))
					(< (ego heading?) 180)
					(not (curRoom script?))
				)
				(cond 
					((not (Btst 212)) (curRoom setScript: sJumpLeftToRight))
					((not local73) (messager say: 11 0 0 0) (= local73 1))
				)
			)
			(
				(and
					(InRect 190 140 193 150 ego)
					(> (ego heading?) 180)
					(not (curRoom script?))
				)
				(curRoom setScript: sJumpRightToLeft)
			)
		)
	)
)

(instance sJumpLeftToRight of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= local0 0)
				(ego
					view: 430
					loop: 0
					setCel: [local31 local0]
					x: [local3 local0]
					y: [local17 local0]
					setScale: 0
					setCycle: 0
				)
				(= ticks 7)
			)
			(1
				(++ local0)
				(ego
					setCel: [local31 local0]
					x: [local3 local0]
					y: [local17 local0]
				)
				(if (== local0 11)
					(theMusic2 number: 5041 setLoop: 1 play:)
				)
				(if (< local0 local2) (-- state))
				(= ticks 7)
			)
			(2
				(ego loop: 1 cel: 0 x: 174 y: 187 setCycle: Fwd)
				(= seconds 2)
			)
			(3
				(if (or (not (Btst 14)) (Btst 15))
					(= next sRockSlide)
				else
					(= next sClimbUp)
				)
				(self dispose:)
			)
		)
	)
)

(instance sJumpRightToLeft of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= local1 0)
				(ego
					view: 430
					loop: 8
					setCel: [local31 local1]
					x: [local45 local1]
					y: [local59 local1]
					setScale: 0
					init:
					setCycle: 0
				)
				(= ticks 7)
			)
			(1
				(++ local1)
				(ego
					setCel: [local31 local1]
					x: [local45 local1]
					y: [local59 local1]
				)
				(if (== local1 11)
					(theMusic2 number: 5041 setLoop: 1 play:)
				)
				(if (< local1 local2) (-- state))
				(= ticks 7)
			)
			(2
				(ego
					setLoop: 9
					setCel: 0
					x: 147
					y: 147
					setCycle: End self
				)
			)
			(3
				(NormalEgo 0)
				(ego loop: 1 x: 134 y: 141 setScale: Scaler 79 26 145 59)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sRockSlide of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic1 number: 35 loop: -1 play:)
				(if (Btst 15)
					(wd40
						cel: 5
						x: 318
						y: 111
						init:
						setCycle: 0
						setStep: 8 12
						moveSpeed: 0
						setLoop: -1
						setLoop: 2
						setMotion: MoveTo 204 146 self
					)
				else
					(wd40 init: setCycle: End self)
				)
			)
			(1 (messager say: 1 0 0 0 self))
			(2
				(wd40 loop: 3 cel: 0 setCycle: End self)
			)
			(3
				(theMusic2 number: 409 setLoop: 1 play:)
				(wd40 setCycle: End self)
			)
			(4
				(theMusic2 number: 409 setLoop: 1 play:)
				(rogRock setCycle: End)
				(ego
					view: 430
					loop: 4
					cel: 0
					x: 174
					y: 189
					cycleSpeed: 5
					setCycle: End self
				)
			)
			(5
				(ego x: 174 y: 200)
				(= ticks 5)
			)
			(6 (ego hide:) (= ticks 5))
			(7
				(EgoDead 19)
				(self dispose:)
			)
		)
	)
)

(instance sClimbUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					view: 430
					loop: 5
					cel: 0
					x: 177
					y: 148
					setCycle: End self
				)
			)
			(1
				(NormalEgo 0)
				(ego loop: 0 x: 194 y: 144 setScale: Scaler 79 26 145 59)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sWD40ShipLands of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(dust cel: (wd40Ship cel?))
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theMusic2 number: 420 setLoop: -1 play:)
				(= seconds 3)
			)
			(1
				(ego init: hide:)
				(dust init:)
				(wd40Ship init: setCycle: CT 3 1 self)
			)
			(2
				(theMusic2 number: 421 setLoop: 1 play:)
				(wd40Ship cel: 4)
				(foot1 init:)
				(foot2 init:)
				(= ticks 10)
			)
			(3
				(foot1 cel: 2)
				(foot2 cel: 3)
				(bigrocks init: setCycle: End self)
			)
			(4
				(bigrocks dispose:)
				(litrock1
					init:
					setCycle: 0
					setLoop: -1
					setLoop: 3
					setStep: 3 10
					moveSpeed: 5
					setMotion: MoveTo 141 200
				)
				(litrock2
					init:
					setCycle: 0
					setLoop: -1
					setLoop: 3
					setStep: 3 9
					moveSpeed: 5
					setMotion: MoveTo 149 200
				)
				(litrock3
					init:
					setCycle: 0
					setLoop: -1
					setLoop: 3
					setStep: 3 8
					moveSpeed: 5
					setMotion: MoveTo 155 200
				)
				(= ticks 5)
			)
			(5
				(foot1 dispose:)
				(foot2 dispose:)
				(wd40Ship cel: 5)
				(= ticks 12)
			)
			(6
				(wd40Ship cel: 6)
				(= seconds 6)
			)
			(7 (curRoom newRoom: 325))
		)
	)
)

(instance sUseTunnel of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (> register 0)
					(ego
						x: 55
						y: 105
						edgeHit: 0
						init:
						setMotion: MoveTo 80 117 self
					)
				else
					(ego setMotion: MoveTo 55 108 self)
				)
			)
			(1
				(theGame handsOn:)
				(if (> register 0)
					(self dispose:)
				else
					(curRoom newRoom: 305)
				)
			)
		)
	)
)

(instance wd40 of Actor
	(properties
		x 204
		y 146
		noun 4
		modNum 301
		yStep 3
		view 430
		loop 2
		priority 13
		signal $0010
		xStep 10
	)
)

(instance rogRock of Prop
	(properties
		x 183
		y 170
		view 430
		loop 7
		priority 10
		signal $4010
		cycleSpeed 5
	)
)

(instance wd40Ship of Prop
	(properties
		x 214
		y 13
		view 436
		priority 2
		signal $4010
		cycleSpeed 10
	)
)

(instance foot1 of Prop
	(properties
		x 175
		y 42
		view 436
		loop 1
		priority 2
		signal $4010
	)
)

(instance foot2 of Prop
	(properties
		x 206
		y 47
		view 436
		loop 1
		cel 1
		priority 2
		signal $4010
	)
)

(instance dust of Prop
	(properties
		x 181
		y 47
		view 436
		loop 4
		priority 1
		signal $4010
	)
)

(instance bigrocks of Prop
	(properties
		x 172
		y 53
		view 436
		loop 2
		priority 3
		signal $4010
	)
)

(instance litrock1 of Actor
	(properties
		x 172
		y 78
		view 436
		loop 3
		cel 2
		priority 3
		signal $4010
	)
)

(instance litrock2 of Actor
	(properties
		x 169
		y 87
		view 436
		loop 3
		cel 1
		priority 3
		signal $4010
	)
)

(instance litrock3 of Actor
	(properties
		x 181
		y 95
		view 436
		loop 3
		priority 3
		signal $4010
	)
)

(instance fire1 of Prop
	(properties
		x 176
		y 39
		noun 8
		view 437
		loop 2
		cel 2
		signal $4000
	)
)

(instance fire2 of Prop
	(properties
		x 214
		y 42
		noun 8
		view 437
		loop 3
		cel 2
		signal $4000
	)
)

(instance heat1 of Prop
	(properties
		x 185
		y 42
		noun 8
		view 437
		loop 4
		cel 6
		signal $4000
	)
)

(instance heat2 of Prop
	(properties
		x 223
		y 45
		noun 8
		view 437
		loop 5
		cel 6
		signal $4000
	)
)

(instance leg1 of View
	(properties
		x 178
		y 48
		noun 8
		view 437
		signal $4000
	)
)

(instance leg2 of View
	(properties
		x 212
		y 54
		noun 8
		view 437
		loop 1
		signal $4000
	)
)

(instance chasm of Feature
	(properties
		x 172
		y 186
		noun 3
		onMeCheck $0002
	)
)

(instance mountains of Feature
	(properties
		x 142
		y 119
		noun 5
		onMeCheck $0004
	)
)

(instance path of Feature
	(properties
		x 288
		y 154
		noun 6
		onMeCheck $0008
	)
)

(instance ship of Feature
	(properties
		x 184
		y 33
		noun 9
		onMeCheck $0010
	)
)

(instance ledge of Feature
	(properties
		x 89
		y 130
		noun 4
		onMeCheck $0200
	)
)

(instance sky of Feature
	(properties
		x 121
		y 30
		noun 9
		onMeCheck $0400
	)
)

(instance cave of Feature
	(properties
		x 68
		y 99
		noun 2
		onMeCheck $0040
	)
)

(instance wall of Feature
	(properties
		x 49
		y 80
		noun 10
		onMeCheck $0020
	)
)
