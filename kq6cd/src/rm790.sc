;;; Sierra Script 1.0 - (do not remove this comment)
(script# 790)
(include sci.sh)
(use Main)
(use CastleRoom)
(use Kq6Procs)
(use n926)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use Timer)
(use Motion)
(use Actor)
(use System)

(public
	rm790 0
)

(local
	local0
	local1 =  1
	theRegister
)
(procedure (localproc_099b &tmp temp0 temp1)
	(= temp1 local0)
	(if argc
		(= local0 (+ 1 (not (== local0 2))))
		(if (curRoom obstacles?)
			((curRoom obstacles?) dispose:)
			(curRoom obstacles: 0)
			(switch local0
				(1
					(curRoom
						addObstacle:
							((Polygon new:)
								type: 3
								init:
									81
									129
									56
									138
									114
									169
									114
									177
									107
									177
									64
									153
									26
									125
									4
									99
									4
									63
									33
									34
									69
									18
									57
									18
									24
									32
									0
									49
									-10
									83
									0
									164
									51
									189
									230
									189
									285
									185
									319
									169
									319
									155
									298
									146
									230
									128
									186
									124
									129
									124
								yourself:
							)
					)
				)
				(else 
					(curRoom
						addObstacle:
							((Polygon new:)
								type: 3
								init:
									191
									124
									134
									124
									90
									128
									58
									134
									36
									154
									44
									154
									72
									134
									85
									134
									48
									158
									0
									158
									1
									169
									35
									185
									90
									189
									269
									189
									320
									164
									330
									83
									320
									49
									296
									32
									263
									18
									251
									18
									287
									34
									316
									63
									316
									99
									294
									125
									256
									153
									213
									177
									206
									177
									206
									169
									226
									129
								yourself:
							)
					)
				)
			)
		)
	)
	(if (!= temp1 local0)
		(= theRegister (not theRegister))
	)
	(ego oldScaleSignal: 0)
	(if theRegister
		(ego setPri: 5 setScale: Scaler 100 56 131 41)
	else
		(ego
			setScale:
				Scaler
				(curRoom maxScaleSize?)
				(curRoom minScaleSize?)
				(curRoom maxScaleY?)
				(curRoom minScaleY?)
			setPri: -1
		)
	)
	((ego scaler?) doit:)
)

(procedure (localproc_0ba1 param1)
	(return
		(if (== local0 2)
			(return (- 320 param1))
		else
			(return param1)
		)
	)
)

(instance rm790 of CastleRoom
	(properties
		noun 3
		picture 790
		style $000a
		walkOffEdge 1
		autoLoad 0
		minScaleSize 60
		minScaleY 122
		maxScaleY 173
	)
	
	(method (init)
		(if (== prevRoomNum 750)
			(= style (| style $4000))
		else
			(= style (& style $bfff))
		)
		(self
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						81
						129
						56
						138
						114
						169
						114
						177
						107
						177
						64
						153
						26
						125
						4
						99
						4
						63
						33
						34
						69
						18
						57
						18
						24
						32
						0
						49
						-10
						83
						0
						164
						51
						189
						230
						189
						285
						185
						319
						169
						319
						155
						298
						146
						230
						128
						186
						124
						129
						124
					yourself:
				)
		)
		(super init: &rest)
		(ego init:)
		(features
			add: stairsFeature doorFeature torchFeature
			eachElementDo: #init
		)
		(switch prevRoomNum
			(750
				(proc926_0)
				(ego posn: 256 19)
				(= theRegister 1)
				(= local0 2)
				(self setScript: enterFromTop)
				(stairs addToPic:)
				(railing addToPic:)
				(doorCover addToPic:)
			)
			(else 
				(rglow init: posn: 228 81 setLoop: 0)
				(lglow init: posn: 96 73 setLoop: 2)
				(rflame init: posn: 229 73 setLoop: 4 setCycle: Fwd)
				(lflame init: posn: 97 71 setLoop: 4 setCycle: Fwd)
				(ego loop: 2 posn: 159 125)
				(vizier init:)
				(= local0 1)
				(if (or (not howFast) (not (HaveMouse)))
					((ScriptID 80 0) loiterTimer: 242)
				else
					((ScriptID 80 0) loiterTimer: 122)
				)
			)
		)
		(localproc_099b)
		((ego scaler?) doit:)
		(if (not script) (theGame handsOn:))
	)
	
	(method (doit &tmp temp0)
		(= temp0 (ego onControl: 1))
		(cond 
			(script 0)
			(
				(or
					(& temp0 $0002)
					(and (== local0 2) (& temp0 $0044))
				)
				(self setScript: changeTowerRooms)
			)
			((and (& temp0 $2000) theRegister)
				(ego
					setScale: Scaler maxScaleSize minScaleSize maxScaleY minScaleY
					setPri: -1
				)
				(= theRegister 0)
			)
			((and (& temp0 $4020) (not theRegister))
				(ego setPri: 13 setScale: Scaler 100 56 131 41)
				((ego scaler?) doit:)
				(= theRegister 1)
			)
			(theRegister
				(cond 
					((< (ego y?) 83) (if (== (ego priority?) 13) (ego priority: 5)))
					((== (ego priority?) 5) (ego priority: 13))
				)
			)
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 752)
	)
	
	(method (newRoom n)
		(lTimer dispose: delete:)
		(rTimer dispose: delete:)
		(super newRoom: n)
	)
	
	(method (doLoiter)
		(curRoom setScript: castSpell 0 2)
	)
)

(instance changeTowerRooms of Script
	(properties)
	
	(method (init)
		(theGame handsOff:)
		(super init: &rest)
		(= register theRegister)
	)
	
	(method (changeState newState &tmp temp0 temp1 temp2)
		(switch (= state newState)
			(0
				((ScriptID 80 0) loiterTimer: 0)
				(= cycles 2)
			)
			(1
				(= temp0 (if register (localproc_0ba1 64) else 49))
				(= temp1 (if register 19 else 149))
				(ego setMotion: MoveTo temp0 temp1 self)
			)
			(2
				(= temp2 1)
				(if register
					(if (== local0 2)
						(curRoom newRoom: 750)
						(= temp2 0)
					else
						(ego posn: 49 149)
					)
				else
					(ego posn: 64 19)
				)
				(if temp2
					(localproc_099b register)
					(if (== local0 1)
						(curRoom style: (& (curRoom style?) $bfff))
					else
						(curRoom style: (| (curRoom style?) $4000))
					)
					(curRoom drawPic: 790)
					(if (== local0 local1) (vizier init:))
					(if (== local0 2)
						(stairs addToPic:)
						(railing addToPic:)
						(doorCover addToPic:)
						(rglow init: posn: 223 73 setLoop: 3)
						(rTimer client: rglow)
						(lglow init: posn: 90 81 setLoop: 1)
						(lTimer client: lglow)
						(rflame init: posn: 222 71 setLoop: 4 setCycle: Fwd)
						(lflame init: posn: 90 73 setLoop: 4 setCycle: Fwd)
					else
						(if (addToPics contains: stairs)
							(stairs dispose:)
							(railing dispose:)
							(doorCover dispose:)
						)
						(rglow init: posn: 228 81 setLoop: 0 cue:)
						(rTimer client: rglow)
						(lglow init: posn: 96 73 setLoop: 2)
						(lTimer client: lglow)
						(rflame init: posn: 229 73 setLoop: 4 setCycle: Fwd)
						(lflame init: posn: 97 71 setLoop: 4 setCycle: Fwd)
					)
					(= cycles 2)
				)
			)
			(3
				(= temp0 (if register 68 else (localproc_0ba1 42)))
				(= temp1 (if register 132 else 45))
				(ego setMotion: PolyPath temp0 temp1 self)
			)
			(4
				(if (not theRegister)
					(ego reset:)
					(theGame handsOn:)
					(if (or (not howFast) (not (HaveMouse)))
						((ScriptID 80 0) loiterTimer: 61)
					else
						((ScriptID 80 0) loiterTimer: 31)
					)
					(self dispose:)
				else
					(curRoom setScript: castSpell 0 3)
				)
			)
		)
	)
)

(instance enterFromTop of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(ego setMotion: MoveTo 278 29 self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance castSpell of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(genie loop: 8 cel: (+ 6 (< (ego x?) 160)) setPri: 15)
				(cond 
					((>= (ego x?) 160)
						(if (== local0 2)
							(genie posn: (localproc_0ba1 257) 162)
						else
							(genie posn: 17 137)
						)
					)
					((== local0 2) (genie posn: (localproc_0ba1 17) 137))
					(else (genie posn: (localproc_0ba1 257) 162))
				)
				(= cycles 2)
			)
			(1
				(self setScript: (ScriptID 752 1) self genie)
			)
			(2 (Face ego genie self))
			(3 (= cycles 5))
			(4
				(switch register
					(1 (messager say: 4 5 0 0 self))
					(2 (messager say: 1 0 2 0 self))
					(3 (messager say: 1 0 4 0 self))
				)
			)
			(5
				(= gEgo ego)
				(= gKillDog getEgo)
				(self setScript: (ScriptID 752 0) 0 genie)
			)
		)
	)
)

(instance getEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					view: 749
					normal: 0
					cel: 0
					loop: (mod (ego cel?) 4)
					cycleSpeed: 8
					setCycle: CT 2 1 self
				)
			)
			(1
				(if
					(or
						(not theRegister)
						(and theRegister (< (ego y?) 43))
					)
					(ego
						loop:
						(switch (ego loop?)
							(0 3)
							(1 2)
							(2 1)
							(3 0)
						)
					)
				)
				(ego setCycle: End self)
			)
			(2
				(if (> (ego loop?) 2)
					(ego
						loop: (+ 4 (== (ego loop?) 3))
						cycleSpeed: 10
						setCycle: End self
					)
				else
					(= cycles 1)
				)
			)
			(3
				(if register (self dispose:) else (EgoDead 18))
			)
		)
	)
)

(instance genie of Actor
	(properties
		x 257
		y 162
		view 702
		loop 7
		priority 13
		signal $0010
	)
)

(instance vizier of Actor
	(properties
		view 145
		signal $2000
		scaleSignal $0001
		scaleX 80
		scaleY 80
	)
	
	(method (init)
		(= x (localproc_0ba1 20))
		(= y 39)
		(super init: &rest)
		(self
			setCycle: Walk
			setStep: 8 4
			setMotion: MoveTo (localproc_0ba1 80) 14 self
		)
	)
	
	(method (cue)
		(++ local1)
		(self dispose:)
	)
)

(instance doorFeature of Feature
	(properties
		noun 4
		onMeCheck $0008
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(curRoom setScript: castSpell 0 1)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (onMe event)
		(return (if (super onMe: event) (== local0 1) else 0))
	)
)

(instance stairsFeature of Feature
	(properties
		noun 5
		onMeCheck $0034
	)
)

(instance torchFeature of Feature
	(properties
		noun 7
		onMeCheck $0080
	)
)

(instance stairs of View
	(properties
		x 84
		y 161
		noun 6
		view 790
		cel 1
		signal $5010
	)
)

(instance railing of View
	(properties
		x 84
		y 161
		noun 6
		view 790
		priority 10
		signal $5010
	)
)

(instance doorCover of View
	(properties
		x 133
		y 65
		onMeCheck $0000
		view 790
		loop 1
		signal $4010
	)
)

(instance mainPoly of Polygon
	(properties)
)

(instance rglow of Prop
	(properties
		view 791
		priority 4
		signal $4810
	)
	
	(method (init)
		(super init: &rest)
		(rTimer setCycle: self (Random 10 30))
	)
	
	(method (cue)
		(self cel: (Random 0 2))
		(rflame cycleSpeed: (Random 4 8))
		(rTimer setCycle: self (Random 2 20))
	)
)

(instance lglow of Prop
	(properties
		view 791
		priority 4
		signal $4810
	)
	
	(method (init)
		(super init: &rest)
		(lTimer setCycle: self (Random 10 30))
	)
	
	(method (cue)
		(self cel: (Random 0 2))
		(lflame cycleSpeed: (Random 4 8))
		(lTimer setCycle: self (Random 2 20))
	)
)

(instance rflame of Prop
	(properties
		view 791
		priority 5
		signal $4810
	)
)

(instance lflame of Prop
	(properties
		view 791
		priority 5
		signal $4810
	)
)

(instance lTimer of Timer
	(properties)
)

(instance rTimer of Timer
	(properties)
)
