;;; Sierra Script 1.0 - (do not remove this comment)
(script# 200)
(include game.sh)
(use Main)
(use Procs)
(use Intrface)
(use Feature)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm200 0
)

(local
	[local0 2]
	local2
	[local3 3]
	local6
	[local7 5]
	local12
	local13
	local14 =  1
	[local15 64]
	[local79 64]
	local143
	local144
	local145
	local146
	local147
	local148
	local149
	local150
	local151
	local152
	local153
	local154
	local155
	local156
	local157
	local158
	local159
	local160
	local161
	local162
	local163
	local164
	local165
	[local166 64] = [39 14 5 46 5 12 9 45 9 9 43 43 43 35 14 167 23 12 39 28 45 3 46 27 9 5 30 29 27 27 39 26 39 12 29 45 29 9 27 43 9 9 39 12 43 3 30 43 29 5 6 30 11 9 7 28 70 10 23 10 23 6 6 30]
	[local230 64] = [39 46 5 134 5 12 1 46 43 43 3 46 7 38 38 14 29 23 6 12 3 46 45 27 29 23 10 29 43 31 23 10 5 38 14 45 41 29 29 43 9 9 3 44 27 3 46 11 7 20 6 30 23 10 5 30 76 5 10 29 3 2 23 10]
	[local294 64] = [5 6 4 12 3 12 9 43 5 10 1 134 3 46 3 14 5 6 4 14 11 13 3 30 5 2 4 12 11 23 10 43 7 36 14 45 45 3 4 10 9 45 11 45 3 10 39 26 76 23 12 29 25 7 10 9 9 29 7 24 11 3 30 11]
	[local358 5] = [0 -6 7 6 -7]
	[local363 5] = [0 -2 8 2 -8]
	[local368 5] = [0 2 9 -2 -9]
	[local373 5] = [0 -5 3 5 -3]
	[local378 5] = [0 -1 4 1 -4]
	[local383 5] = [0 3 5 -3 -5]
	[local388 5] = [0 -4 -1 4 1]
	[local393 5] = [0 4 1 -4 -1]
	[local398 5] = [0 1 -4 -1 4]
	local403 =  16
	local404 =  -16
	[local405 6] = [4 1 2 8 4 1]
	[local411 10] = [0 64 0 128 0 64 {N} {E} {S} {W}]
	local421
	local422
	[local423 15] = [1 4 8 2 5 12 10 3 13 14 11 7 6 9 15]
	[local438 4] = [73 107 107 73]
	[local442 4] = [52 52 79 79]
)
(procedure (localproc_04a6 param1 param2 param3)
	(switch local143
		(param1 (= local165 0))
		(param2 (= local163 1))
		(param3 (= local162 1))
	)
)

(procedure (localproc_04d1)
	(= local154 [local405 local143])
	(= local155 [local405 (mod (+ local143 2) 4)])
	(= local153 [local405 (+ local143 1)])
	(= local152 [local405 (- local143 1)])
	(= local158 [local411 local143])
	(= local157 [local411 (+ local143 1)])
	(= local156 [local411 (- local143 1)])
	(= local151 (+ local12 [local358 local143]))
	(= local150 (+ local12 [local363 local143]))
	(= local149 (+ local12 [local368 local143]))
	(= local148 (+ local12 [local373 local143]))
	(= local147 (+ local12 [local378 local143]))
	(= local146 (+ local12 [local383 local143]))
	(= local145 (+ local12 [local388 local143]))
	(= local144 (+ local12 [local393 local143]))
)

(procedure (localproc_0566 &tmp temp0 temp1 temp2 temp3)
	(localproc_04d1)
	(= local165 1)
	(= local164 1)
	(= local163 0)
	(= local162 0)
	(if (< (mod (= temp3 (mod local12 16)) 4) 2)
		(if (== local143 1) (= local164 0))
		(if (< (mod temp3 4) 1) (localproc_04a6 1 2 4))
	)
	(if (> (mod temp3 4) 1)
		(if (== local143 3) (= local164 0))
		(if (> (mod temp3 4) 2) (localproc_04a6 3 4 2))
	)
	(if (> (/ temp3 4) 1)
		(if (== local143 2) (= local164 0))
		(if (> (/ temp3 4) 2) (localproc_04a6 2 3 1))
	)
	(if (< (/ temp3 4) 2)
		(if (== local143 4) (= local164 0))
		(if (< (/ temp3 4) 1) (localproc_04a6 4 1 3))
	)
	(if (& [local15 local12] local154)
		(= temp0 0)
	else
		(= temp0 1)
	)
	(if
		(or
			local162
			(and (not temp0) (& [local15 local12] local153))
		)
		(= temp1 0)
	else
		(= temp1 1)
	)
	(if
		(and
			local163
			(or (not temp0) (& [local15 local12] local152))
		)
		(= temp2 0)
	else
		(= temp2 1)
	)
	(if local164
		(if temp0 (DrawCel local421 0 2 161 68 15))
		(cond 
			((and temp1 (& [local15 local149] local153)) (DrawCel local421 2 2 245 67 15))
			((and temp1 (& [local15 local149] local157)) (DrawCel local421 2 5 245 67 15))
		)
		(cond 
			(
			(and temp2 (& [local15 (+ local12 local151)] local152)) (DrawCel local421 1 2 156 68 15))
			(
			(and temp2 (& [local15 (+ local12 local151)] local156)) (DrawCel local421 1 5 156 68 15))
		)
		(cond 
			((and temp0 (& [local15 local149] local154)) (DrawCel local421 3 2 218 75 15))
			((and temp0 (& [local15 local149] local158)) (DrawCel local421 6 2 218 75 15))
		)
		(cond 
			((and temp0 (& [local15 local151] local154)) (DrawCel local421 3 2 169 75 15))
			((and temp0 (& [local15 local151] local158)) (DrawCel local421 6 2 169 75 15))
		)
	)
	(DrawCel local421 0 0 156 48 15)
	(DrawCel local421 0 1 156 83 15)
	(if local164
		(if temp1
			(if (& [local15 (+ local12 local149)] $0020)
				(DrawCel local421 10 2 218 68 15)
			)
			(if (& [local15 local149] $0010)
				(DrawCel local421 12 2 218 83 15)
			)
		)
		(if temp2
			(if (& [local15 local151] $0020)
				(DrawCel local421 11 2 156 68 15)
			)
			(if (& [local15 local151] $0010)
				(DrawCel local421 13 2 156 83 15)
			)
		)
		(if temp0
			(cond 
				((& [local15 local150] local154) (DrawCel local421 3 2 194 76 15))
				((& [local15 local150] local158) (DrawCel local421 6 2 194 76 15))
			)
			(if (& [local15 local150] $0020)
				(DrawCel local421 8 2 186 68 15)
			)
			(if (& [local15 local150] $0010)
				(DrawCel local421 9 2 187 83 15)
			)
			(cond 
				((& [local15 local150] local153) (DrawCel local421 2 2 217 68 15))
				((& [local15 local150] local157) (DrawCel local421 2 5 217 68 15))
			)
			(cond 
				((& [local15 local150] local152) (DrawCel local421 1 2 184 68 15))
				((& [local15 local150] local156) (DrawCel local421 1 2 184 68 15))
			)
		)
	)
	(if local165
		(if temp0
			(if (& [local15 local147] $0020)
				(DrawCel local421 8 1 173 58 15)
				(= [local79 local147] (| [local79 local147] $0020))
			)
			(if (& [local15 local147] $0010)
				(DrawCel local421 9 1 172 91 15)
				(= [local79 local147] (| [local79 local147] $0010))
			)
		)
		(if temp1
			(if (& [local15 local146] $0020)
				(DrawCel local421 10 1 228 58 15)
			)
			(if (& [local15 local146] $0010)
				(DrawCel local421 12 1 228 91 15)
			)
			(cond 
				((& [local15 local146] local154) (DrawCel local421 4 1 228 68 15))
				((& [local15 local146] local158) (DrawCel local421 4 4 228 68 15))
			)
		)
		(if temp2
			(if (& [local15 local148] $0020)
				(DrawCel local421 11 1 155 58 15)
			)
			(if (& [local15 local148] $0010)
				(DrawCel local421 13 1 156 91 15)
			)
			(cond 
				((& [local15 local148] local154) (DrawCel local421 5 1 156 68 15))
				((& [local15 local148] local158) (DrawCel local421 5 4 156 68 15))
			)
		)
		(if temp0
			(cond 
				((& [local15 local147] local153)
					(DrawCel local421 2 1 227 57 15)
					(= [local79 local147] (| [local79 local147] local153))
				)
				((& [local15 local147] local157) (DrawCel local421 2 4 227 57 15))
			)
			(cond 
				((& [local15 local147] local152)
					(DrawCel local421 1 1 166 57 15)
					(= [local79 local147] (| [local79 local147] local152))
				)
				((& [local15 local147] local156) (DrawCel local421 1 4 166 57 15))
			)
			(cond 
				((& [local15 local147] local154)
					(DrawCel local421 3 1 186 68 15)
					(= [local79 local147] (| [local79 local147] local154))
				)
				((& [local15 local147] local158) (DrawCel local421 6 1 186 68 15))
			)
		)
	)
	(if (& [local15 local12] $0020)
		(DrawCel local421 8 0 156 48 15)
		(= [local79 local12] (| [local79 local12] $0020))
	)
	(if (& [local15 local12] $0010)
		(DrawCel local421 9 0 156 101 15)
		(= [local79 local12] (| [local79 local12] $0010))
	)
	(cond 
		((& [local15 local12] local153)
			(DrawCel local421 2 0 242 47 15)
			(= [local79 local12] (| [local79 local12] local153))
		)
		((& [local15 local12] local157) (DrawCel local421 2 3 242 47 15))
		(temp1
			(if (& [local15 local144] $0020)
				(DrawCel local421 10 0 242 48 15)
				(= [local79 local144] (| [local79 local144] $0020))
			)
			(if (& [local15 local144] $0010)
				(DrawCel
					local421
					12
					0
					242
					101
					15
					(= [local79 local144] (| [local79 local144] $0010))
				)
			)
			(cond 
				((& [local15 local144] local154)
					(DrawCel local421 4 0 243 58 15)
					(= [local79 local144] (| [local79 local144] local154))
				)
				((& [local15 local144] local158) (DrawCel local421 4 3 243 58 15))
			)
		)
	)
	(cond 
		((& [local15 local12] local152)
			(DrawCel local421 1 0 155 47 15)
			(= [local79 local12] (| [local79 local12] local152))
		)
		((& [local15 local12] local156) (DrawCel local421 1 3 155 47 15))
		(temp2
			(if (& [local15 local145] $0020)
				(DrawCel local421 11 0 155 48 15)
				(= [local79 local145] (| [local79 local145] $0020))
			)
			(if (& [local15 local145] $0010)
				(DrawCel local421 13 0 155 101 15)
				(= [local79 local145] (| [local79 local145] $0010))
			)
			(cond 
				((& [local15 local145] local154)
					(DrawCel local421 5 0 156 58 15)
					(= [local79 local145] (| [local79 local145] local154))
				)
				((& [local15 local145] local158) (DrawCel local421 5 3 156 58 15))
			)
		)
	)
	(if (not (& [local15 local12] $0020))
		(DrawCel local421 14 0 156 48 15)
	)
	(if (not (& [local15 local12] $0010))
		(DrawCel local421 15 0 156 101 15)
	)
	(cond 
		((& [local15 local12] local154)
			(DrawCel local421 3 0 171 58 15)
			(= [local79 local12] (| [local79 local12] local154))
		)
		((& [local15 local12] local158) (DrawCel local421 6 0 171 58 15))
	)
	(switch local14
		(1 (localproc_0e81))
		(2 (localproc_0f1e))
	)
	(switch (/ local12 16)
		(0 (southMazeDoor show:))
		(1 (southMazeDoor hide:))
		(2 (northMazeDoor hide:))
		(3 (northMazeDoor show:))
	)
)

(procedure (localproc_0e81 &tmp temp0 temp1 temp2 temp3 temp4)
	(= temp4 (/ local12 16))
	(= temp1 (/ (mod local12 16) 4))
	(= temp0 (mod (mod local12 16) 4))
	(= temp3 (+ 76 (* temp0 4) (* temp1 7)))
	(= temp2 (+ (- 75 (* temp4 8)) (* temp0 4)))
	(Graph GFillRect 49 75 55 100 1 1 -1 -1)
	(DrawCel 201 0 0 74 53 15)
	(DrawCel 201 0 1 74 (- 77 (* temp4 8)) 15)
	(DrawCel 201 3 local143 121 54 15)
	(positionIcon x: temp3 y: temp2)
)

(procedure (localproc_0f1e &tmp temp0 temp1 temp2 temp3 temp4 temp5 temp6 temp7 temp8 temp9)
	(Graph GFillRect 49 67 94 121 1 1 -1 -1)
	(Graph GShowBits 49 67 94 121 1)
	(= temp6 (* (/ local12 16) 16))
	(= temp0 0)
	(while (< temp0 16)
		(if [local79 (= temp2 (+ temp6 temp0))]
			(= temp5 (mod [local79 temp2] 16))
			(= temp1 0)
			(while (!= temp5 [local423 temp1])
				(++ temp1)
			)
			(= temp3 (+ (* (/ temp0 4) 10) 74))
			(= temp4 (+ (* (mod temp0 4) 8) 53))
			(DrawCel 201 4 temp1 temp3 temp4 15)
			(cond 
				((& [local79 temp2] $0020) (DrawCel 201 7 0 (+ temp3 1) (+ temp4 1) 15))
				((& [local79 temp2] $0010) (DrawCel 201 7 1 (+ temp3 1) (+ temp4 1) 15))
			)
		)
		(++ temp0)
	)
	(= temp7 0)
	(while (< temp7 4)
		(DrawCel 201 6 temp7 [local438 temp7] [local442 temp7] 15)
		(++ temp7)
	)
	(= temp9 (/ (mod local12 16) 4))
	(= temp8 (mod (- local12 temp6) 4))
	(positionIcon
		x: (+ 75 (* temp9 10))
		y: (+ 53 (* temp8 8))
	)
	(DrawCel 201 5 local143 121 54 15)
)

(instance rm200 of Room
	(properties
		lookStr {You are in an elevator car.}
		picture 200
		style $0007
	)
	
	(method (init)
		(LoadMany 128 202 201 200)
		(super init:)
		(levelIndicator init: setCycle: Forward)
		(flasher init: setCycle: Forward)
		(leftArrow init:)
		(rightArrow init:)
		(forwardArrow init:)
		(backwardArrow init:)
		(downMover init:)
		(upMover init:)
		(changeButton init:)
		(= local143 1)
		(= local12 15)
		(if
		(and (!= prevRoomNum 180) (!= prevRoomNum 380))
			(= prevRoomNum 280)
		)
		(switch prevRoomNum
			(280
				(= local13 0)
				(while (< local13 64)
					(= [local15 local13] [local166 local13])
					(++ local13)
				)
				(= local421 202)
				(= local12 15)
				(= local159 320)
				(= local160 3)
				(= local161 2)
			)
			(380
				(= local13 0)
				(while (< local13 64)
					(= [local15 local13] [local230 local13])
					(++ local13)
				)
				(= local421 402)
				(= local12 3)
				(= local159 420)
				(= local160 0)
				(= local161 2)
			)
			(180
				(= local13 0)
				(while (< local13 64)
					(= [local15 local13] [local294 local13])
					(++ local13)
				)
				(= local421 202)
				(= local12 11)
				(= local159 220)
				(= local160 2)
				(= local161 0)
			)
		)
		(southMazeDoor x: (+ 91 (* local160 7)) init: hide:)
		(northMazeDoor x: (+ 77 (* local161 7)) init: hide:)
		(localproc_04d1)
		(localproc_0566)
		(= local13 0)
		(while (< local13 64)
			(= [local79 local13] 0)
			(++ local13)
		)
		(positionIcon init: setCycle: Forward)
		(Graph GFillRect 49 67 96 131 1 1 -1 -1)
		(localproc_0e81)
		(controlPanel init:)
		(elevWindow init:)
		(keyDownHandler addToFront: self)
		(directionHandler addToFront: self)
		(self setScript: demo)
	)
	
	(method (dispose)
		(keyDownHandler delete: self)
		(directionHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (& (event type?) direction)
			(switch (event message?)
				(dirNE
					(upMover doVerb: verbDo)
				)
				(dirSE
					(downMover doVerb: verbDo)
				)
				(dirW
					(leftArrow doVerb: verbDo)
				)
				(dirE
					(rightArrow doVerb: verbDo)
				)
				(dirN
					(forwardArrow doVerb: verbDo)
				)
				(dirS
					(backwardArrow doVerb: verbDo)
				)
			)
		else
			(super handleEvent: event &rest)
		)
	)
)

(instance demo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks 60))
			(1
				(= local2
					(DoDisplay {Let's take the ELEVATOR!} 67 5 150 70 320)
				)
				(= ticks 200)
			)
			(2
				(DoDisplay local2)
				(= local6
					(DoDisplay
						{But HOW do these controls work?}
						67
						70
						150
						70
						320
					)
				)
				(= ticks 1)
			)
			(3
				(upMover doVerb: verbDo)
				(= ticks 60)
			)
			(4
				(leftArrow doVerb: verbDo)
				(= ticks 60)
			)
			(5
				(forwardArrow doVerb: verbDo)
				(= ticks 60)
			)
			(6
				(downMover doVerb: verbDo)
				(= ticks 60)
			)
			(7
				(forwardArrow doVerb: verbDo)
				(= ticks 5)
			)
			(8
				(DoDisplay local6)
				(curRoom newRoom: 360 8)
			)
		)
	)
)

(instance controlPanel of Feature
	(properties
		x 128
		y 114
		z 58
		nsTop 47
		nsLeft 118
		nsBottom 65
		nsRight 138
		description {elevator control panel}
		sightAngle 180
		onMeCheck $4000
		lookStr {The control panel has six differently-colored buttons.}
	)
)

(instance elevWindow of Feature
	(properties
		x 154
		y 114
		z 85
		nsTop 18
		nsLeft 124
		nsBottom 40
		nsRight 184
		description {window in the elevator door}
		sightAngle 180
		onMeCheck $0080
		lookStr {Through the elevator window, you can see shafts going off in all directions.}
	)
)

(instance leftArrow of Prop
	(properties
		x 81
		y 131
		view 200
		cel 1
		signal $0004
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb verbDo)
				(if (not (-- local143)) (= local143 4))
				(localproc_0566)
			)
			((== theVerb verbLook)
				(= lookStr {*** This button will turn you to the left})
				(super doVerb: theVerb &rest)
			)
			(else (super doVerb: theVerb &rest))
		)
	)
)

(instance rightArrow of Prop
	(properties
		x 117
		y 131
		view 200
		loop 1
		cel 1
		signal $0004
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb 3)
				(if (> (++ local143) 4) (= local143 1))
				(localproc_0566)
			)
			((== theVerb 2)
				(= lookStr {*** This button will turn you to the right})
				(super doVerb: theVerb &rest)
			)
			(else (super doVerb: theVerb &rest))
		)
	)
)

(instance forwardArrow of Prop
	(properties
		x 99
		y 112
		view 200
		loop 2
		cel 1
		signal $0004
	)
	
	(method (doVerb theVerb &tmp temp0)
		(cond 
			((== theVerb 3)
				(cond 
					((& [local15 local12] local158)
						(cond 
							((== local158 64) (Print 200 0) (curRoom newRoom: local159))
							((== local158 128) (Print 200 1) (curRoom newRoom: prevRoomNum))
						)
					)
					((& [local15 local12] local154) (ShakeScreen 2 2) (Print 200 2))
					(else
						(= local12 (+ local12 [local378 local143]))
						(localproc_0566)
					)
				)
			)
			((== theVerb 2)
				(= lookStr {*** This button will move you forward})
				(super doVerb: theVerb &rest)
			)
			(else (super doVerb: theVerb &rest))
		)
	)
)

(instance backwardArrow of Prop
	(properties
		x 99
		y 131
		view 200
		loop 3
		cel 1
		signal $0004
	)
	
	(method (doVerb theVerb &tmp temp0)
		(cond 
			((== theVerb 3)
				(cond 
					((& [local15 local12] (= temp0 [local411 2]))
						(cond 
							((== temp0 64) (Print 200 0) (curRoom newRoom: local159))
							((== temp0 128) (Print 200 1) (curRoom newRoom: prevRoomNum))
						)
					)
					((& [local15 local12] local155) (ShakeScreen 2 2) (Print 200 2))
					(else
						(= local12 (+ local12 [local398 local143]))
						(localproc_0566)
					)
				)
			)
			((== theVerb 2)
				(= lookStr {*** This button will move you forward})
				(super doVerb: theVerb &rest)
			)
			(else (super doVerb: theVerb &rest))
		)
	)
)

(instance downMover of Prop
	(properties
		x 91
		y 123
		view 200
		loop 4
		cel 1
		signal $0004
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb 3)
				(if
				(and (> local12 15) (& [local15 local12] $0010))
					(= local12 (+ local12 local404))
					(localproc_0566)
				else
					(ShakeScreen 2 1)
					(Print 200 3)
				)
			)
			((== theVerb 2)
				(= lookStr {*** This button will move you down a level})
				(super doVerb: theVerb &rest)
			)
			(else (super doVerb: theVerb &rest))
		)
	)
)

(instance upMover of Prop
	(properties
		x 108
		y 123
		view 200
		loop 5
		cel 1
		signal $0004
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb 3)
				(if
				(and (< local12 51) (& [local15 local12] $0020))
					(= local12 (+ local12 local403))
					(localproc_0566)
				else
					(ShakeScreen 2 1)
					(Print 200 4)
				)
			)
			((== theVerb 2)
				(= lookStr {*** This button will move you up a level})
				(super doVerb: theVerb &rest)
			)
			(else (super doVerb: theVerb &rest))
		)
	)
)

(instance positionIcon of Actor
	(properties
		x 76
		y 51
		view 201
		loop 1
		priority 15
		signal $0010
	)
)

(instance northMazeDoor of Actor
	(properties
		y 49
		description {North Door}
		lookStr {This is the north door out of the maze}
		view 201
		loop 2
		cel 1
	)
)

(instance southMazeDoor of Actor
	(properties
		y 89
		description {South Door}
		lookStr {This is the south door out of the maze}
		view 201
		loop 2
		priority 15
		signal $0010
	)
)

(instance changeButton of Actor
	(properties
		x 153
		y 127
		view 200
		loop 7
		cel 1
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb 3)
				(if (!= arcadeLevel 2)
					(if (== local14 1)
						(= local14 2)
						(positionIcon loop: 8)
						(southMazeDoor x: (+ 79 (* local160 10)) y: 78)
						(northMazeDoor x: (+ 76 (* local161 10)) y: 49)
					else
						(= local14 1)
						(positionIcon loop: 1)
						(southMazeDoor x: (+ 89 (* local160 7)) y: 95)
						(northMazeDoor x: (+ 77 (* local161 7)) y: 49)
					)
					(Graph GFillRect 49 67 94 121 1 1 -1 -1)
					(Graph GShowBits 49 67 94 121 1)
					(localproc_0566)
				)
			)
			((== theVerb verbLook)
				(= lookStr {*** This button will move you up a level})
				(super doVerb: theVerb &rest)
			)
			(else (super doVerb: theVerb &rest))
		)
	)
)

(instance levelIndicator of Actor
	(properties
		x 242
		y 132
		view 200
		loop 8
		signal $0202
		cycleSpeed 18
	)
)

(instance flasher of Actor
	(properties
		x 193
		y 125
		view 200
		loop 6
		signal $0202
		cycleSpeed 18
	)
)
