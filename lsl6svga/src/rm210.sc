;;; Sierra Script 1.0 - (do not remove this comment)
(script# 210)
(include sci.sh)
(use Main)
(use fileScr)
(use LarryRoom)
(use Polygon)
(use Feature)
(use Timer)
(use System)

(public
	rm210 0
)

(local
	local0
	local1
	local2 =  -1
)
(instance rm210 of LarryRoom
	(properties
		noun 4
		picture 210
		horizon 0
	)
	
	(method (init)
		(super init: &rest)
		(if
		(and (not (theMusic2 handle?)) (!= prevRoomNum 200))
			(theMusic2 number: 200 loop: -1 setVol: 80 play:)
		)
		(UnLoad 128 80)
		(theEyes init:)
		(lArm init:)
		(rArm init:)
		(hair init:)
		(lips init:)
		(breasts init:)
		(hands init:)
		((ScriptID 0 11) init: self)
		(theGame handsOff:)
		(if
			(and
				(Btst 21)
				(not (Btst 20))
				(or
					(Btst 183)
					(Btst 177)
					(Btst 180)
					(Btst 182)
					global189
				)
			)
			(= global189 0)
			(self setScript: happyGammieScr)
		else
			(talkTimer setCycle: talkTimer 2)
		)
		(Load rsSCRIPT 1800)
		(Load rsSCRIPT 1801)
	)
	
	(method (dispose)
		((ScriptID 0 11) dispose:)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(return
			(if (== theVerb 2)
				(cond 
					((Btst 183) (self setScript: happyGammieScr))
					((Btst 21) (messager say: 4 2 12) (return 1))
					(else
						(switch (++ local0)
							(1
								(if (ego has: 22)
									(++ local0)
									(curRoom doVerb: theVerb)
								else
									(messager say: 4 2 5)
								)
							)
							(2
								(curRoom setScript: giveLarryHisKey 0 0)
							)
							(3 (messager say: 4 2 7))
							(4 (messager say: 4 2 8))
							(5 (messager say: 4 2 9))
							(6 (messager say: 4 2 10))
							(7
								(curRoom setScript: tellLarryAboutMachine)
							)
						)
						(return 1)
					)
				)
			else
				(super doVerb: theVerb)
			)
		)
	)
	
	(method (cue)
		(curRoom newRoom: 200)
	)
	
	(method (newRoom n)
		(theMusic2 fade: 127 10 10 0 client: 0)
		(talkTimer dispose: delete:)
		(super newRoom: n)
	)
)

(instance theEyes of Feature
	(properties
		noun 3
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 151 31 181 31 181 44 151 44
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(if (not (OneOf theVerb 2 6))
			(super doVerb: theVerb)
		else
			(curRoom doVerb: theVerb)
		)
	)
)

(instance rArm of Feature
	(properties
		noun 1
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						112
						108
						115
						86
						129
						78
						137
						80
						144
						103
						135
						108
						122
						135
						147
						135
						147
						139
						98
						139
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(if (not (OneOf theVerb 2 6))
			(super doVerb: theVerb)
		else
			(curRoom doVerb: theVerb)
		)
	)
)

(instance lArm of Feature
	(properties
		noun 1
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 192 66 203 71 206 81 198 95
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(if (not (OneOf theVerb 2 6))
			(super doVerb: theVerb)
		else
			(curRoom doVerb: theVerb)
		)
	)
)

(instance hair of Feature
	(properties
		noun 5
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						131
						55
						134
						33
						148
						17
						148
						11
						153
						5
						176
						5
						189
						13
						192
						43
						199
						53
						200
						66
						190
						73
						178
						69
						178
						56
						181
						53
						180
						32
						167
						27
						151
						33
						148
						45
						153
						49
						154
						69
						144
						80
						132
						77
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(if (!= theVerb 2)
			(super doVerb: theVerb)
		else
			(curRoom doVerb: theVerb)
		)
	)
)

(instance hands of Feature
	(properties
		noun 6
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 159 128 175 124 193 124 201 120 205 130 202 139 146 139 146 135
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(if (not (OneOf theVerb 2 6))
			(super doVerb: theVerb)
		else
			(curRoom doVerb: theVerb)
		)
	)
)

(instance breasts of Feature
	(properties
		noun 2
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						135
						111
						157
						97
						176
						107
						184
						95
						198
						95
						209
						103
						207
						117
						194
						124
						172
						125
						151
						134
						146
						136
						141
						133
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(switch (++ local1)
					(1 (messager say: 2 1 1))
					(2 (messager say: 2 1 2))
					(else  (messager say: 2 1 3))
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance lips of Feature
	(properties
		noun 7
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 177 49 177 57 157 57 157 49
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(if (not (OneOf theVerb 2 6))
			(super doVerb: theVerb)
		else
			(curRoom doVerb: theVerb)
		)
	)
)

(instance happyGammieScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(messager sayRange: 4 2 4 1 2 self)
			)
			(2
				(theGame changeScore: 5 163)
				(messager say: 4 2 4 3 self)
			)
			(3
				(messager sayRange: 4 2 4 4 5 self)
			)
			(4
				(= global171 2)
				(curRoom newRoom: 200)
			)
		)
	)
)

(instance talkTimer of Timer
	(properties)
	
	(method (cue)
		(switch global207
			(2
				(= gGButtonBarCurIcon (ScriptID 0 7))
			)
			(1
				(= gGButtonBarCurIcon (ScriptID 0 4))
			)
		)
		(theGame handsOn:)
		(cond 
			((Btst 56) (Bclr 56) (curRoom doVerb: 7))
			((and (not (Btst 106)) (Btst 55)) (Bset 106) (messager say: 0 0 14))
		)
	)
)

(instance giveLarryHisKey of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= register 0)
				(= cycles 2)
			)
			(1
				(messager say: 4 2 6 (++ register) self)
			)
			(2
				(switch register
					(3
						(= state 0)
						(ego get: 22)
						(theGame changeScore: 2 164 handsOn:)
					)
					(4)
					(else  (= state 0))
				)
				(= cycles 2)
			)
			(3 (self dispose:))
		)
	)
)

(instance tellLarryAboutMachine of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset 21)
				(messager sayRange: 4 2 11 1 3 self)
			)
			(1
				(theGame changeScore: 3 162)
				(messager sayRange: 4 2 11 4 5 self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)
