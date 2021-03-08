;;; Sierra Script 1.0 - (do not remove this comment)
(script# 242)
(include sci.sh)
(use Main)
(use rm241)
(use Print)
(use Feature)
(use Actor)
(use System)

(public
	keyStuff 0
	proc242_1 1
)

(local
	local0
	[local1 11]
	local12
	theRegister
)
(procedure (proc242_1)
	(d3 startUpd:)
	(d2 startUpd:)
	(d1 startUpd:)
	(d0 startUpd:)
	(if (and (< 0 (d0 loop?)) (< (d0 loop?) 10))
		(d0 loop: (- (d0 loop?) 1))
	else
		(if (== (d1 loop?) 15)
			(d0 loop: 15)
		else
			(d0 loop: 9)
		)
		(if (and (< 0 (d1 loop?)) (< (d1 loop?) 6))
			(d1 loop: (- (d1 loop?) 1))
		else
			(if (== (d2 loop?) 15)
				(d1 loop: 15)
			else
				(d1 loop: 5)
			)
			(if (and (< 0 (d2 loop?)) (< (d2 loop?) 10))
				(d2 loop: (- (d2 loop?) 1))
			else
				(if (== (d3 loop?) 15)
					(d2 loop: 15)
				else
					(d2 loop: 9)
				)
				(if (and (< 0 (d3 loop?)) (< (d3 loop?) 10))
					(d3 loop: (- (d3 loop?) 1))
				else
					(d3 loop: 15)
				)
			)
		)
	)
)

(procedure (localproc_006c param1)
	(= local12 1)
	(= [local1 local0] param1)
	(= local0 (mod (++ local0) 10))
	(= local12 0)
)

(procedure (localproc_0083)
	(= local12 1)
	(if (> local0 0)
		(return [local1 (-- local0)])
	else
		(return -1)
	)
	(return (= local12 0))
)

(procedure (localproc_009d param1)
	(if (< param1 10)
		(if (== (d3 loop?) 15)
			(d3 startUpd: setLoop: (d2 loop?))
			(d2 startUpd: setLoop: (d1 loop?))
			(d1 startUpd: setLoop: (d0 loop?))
			(d0 startUpd: setLoop: param1)
		)
		(if (!= (d2 loop?) 15)
			(colon init:)
		else
			(colon dispose:)
		)
	)
)

(procedure (localproc_0266 param1)
	(return
		(cond 
			(
				(and
					(InRect
						(+ (pad x?) 15)
						(+ (pad y?) 27)
						(+ (pad x?) 63)
						(+ (pad y?) 66)
						param1
					)
					(not (proc241_3))
				)
				(return
					(+
						(* (/ (- (param1 y?) (+ (pad y?) 27)) 13) 3)
						1
						(/ (- (param1 x?) (+ (pad x?) 15)) 16)
					)
				)
			)
			(
				(and
					(InRect
						(+ (pad x?) 15)
						(+ (pad y?) 27 52)
						(+ (pad x?) 15 48)
						(+ (pad y?) 27 65)
						param1
					)
					(localproc_062a)
					(not (proc241_3))
				)
				(return 14)
			)
			(
				(and
					(InRect
						(+ (pad x?) 15 53)
						(- (+ (pad y?) 27) 13)
						(+ (pad x?) 15 101)
						(+ (pad y?) 27)
						param1
					)
					(not (proc241_3))
				)
				(proc241_2 0)
				(return 10)
			)
			(
				(and
					(InRect
						(+ (pad x?) 15 53)
						(+ (pad y?) 27)
						(+ (pad x?) 15 101)
						(+ (pad y?) 27 13)
						param1
					)
					(not (proc241_3))
				)
				(proc241_2 1)
				(return 11)
			)
			(
				(and
					(InRect
						(+ (pad x?) 15 53)
						(+ (pad y?) 27 13)
						(+ (pad x?) 15 101)
						(+ (pad y?) 27 26)
						param1
					)
					(not (proc241_3))
				)
				(proc241_2 2)
				(return 12)
			)
			(
				(and
					(InRect
						(+ (pad x?) 15 16)
						(+ (pad y?) 27 39)
						(+ (pad x?) 15 32)
						(+ (pad y?) 27 52)
						param1
					)
					(not (proc241_3))
				)
				(return 0)
			)
			(
				(InRect
					(+ (pad x?) 15 32)
					(+ (pad y?) 27 39)
					(+ (pad x?) 15 48)
					(+ (pad y?) 27 52)
					param1
				)
				(return 13)
			)
			(else (return -1))
		)
	)
)

(procedure (localproc_0509 param1)
	(switch param1
		(0 16)
		(14 0)
		(10 53)
		(11 53)
		(12 53)
		(13 32)
		(else 
			(* (mod (- param1 1) 3) 16)
		)
	)
)

(procedure (localproc_0556 param1)
	(switch param1
		(0 39)
		(14 52)
		(10 -13)
		(11 0)
		(12 13)
		(13 39)
		(else 
			(* (/ (- param1 1) 3) 13)
		)
	)
)

(procedure (localproc_05a3 param1 &tmp temp0 temp1)
	(d0 loop: 15)
	(d1 loop: 15)
	(d2 loop: 15)
	(d3 loop: 15)
	(= temp0 (/ param1 60))
	(= temp1 (mod param1 60))
	(if temp0
		(d2 loop: (mod temp0 10))
		(if (= temp0 (/ temp0 10)) (d3 loop: (mod temp0 10)))
	)
	(if temp1
		(d0 loop: (mod temp1 10))
		(if (= temp1 (/ temp1 10)) (d1 loop: (mod temp1 10)))
	)
)

(procedure (localproc_062a &tmp temp0 temp1 d0Loop d1Loop d2Loop d3Loop)
	(= d0Loop (d0 loop?))
	(= d1Loop (d1 loop?))
	(= d2Loop (d2 loop?))
	(= d3Loop (d3 loop?))
	(if (== (d0 loop?) 15) (= d0Loop 0))
	(if (== (d1 loop?) 15) (= d1Loop 0))
	(if (== (d2 loop?) 15) (= d2Loop 0))
	(if (== (d3 loop?) 15) (= d3Loop 0))
	(= temp1 (+ d0Loop (* d1Loop 10)))
	(return (+ (* (= temp0 (+ d2Loop (* d3Loop 10))) 60) temp1))
)

(instance d0 of Prop
	(properties
		view 264
		loop 15
		priority 15
		signal $4010
	)
	
	(method (init)
		(self loop: 15 signal: 16400)
		(self posn: (+ 20 (pad x?) 30) (+ 17 (pad y?)))
		(super init: &rest)
	)
)

(instance d1 of Prop
	(properties
		view 264
		loop 15
		priority 15
		signal $4010
	)
	
	(method (init)
		(self loop: 15 signal: 16400)
		(self posn: (+ 20 (pad x?) 20) (+ 17 (pad y?)))
		(super init: &rest)
	)
)

(instance d2 of Prop
	(properties
		view 264
		loop 15
		priority 15
		signal $4010
	)
	
	(method (init)
		(self loop: 15 signal: 16400)
		(self posn: (+ 20 (pad x?) 10) (+ 17 (pad y?)))
		(super init: &rest)
	)
)

(instance d3 of Prop
	(properties
		view 264
		loop 15
		priority 15
		signal $4010
	)
	
	(method (init)
		(self loop: 15 signal: 16400)
		(self posn: (+ 20 (pad x?) 0) (+ 17 (pad y?)))
		(super init: &rest)
	)
)

(instance tempKey of Prop
	(properties
		view 264
		cel 1
		priority 15
		signal $4010
	)
	
	(method (show param1)
		(self
			posn:
				(+ (localproc_0509 param1) (pad x?) 15)
				(+ (localproc_0556 param1) (pad y?) 27)
			setLoop: param1
			setCel: 1
		)
		(super show: &rest)
	)
)

(instance tempKey2 of Prop
	(properties
		view 264
		cel 1
		priority 15
		signal $4010
	)
	
	(method (show &tmp temp0)
		(= temp0 (+ (proc241_2) 10))
		(self
			posn:
				(+ (localproc_0509 temp0) (pad x?) 15)
				(+ (localproc_0556 temp0) (pad y?) 27)
			setLoop: temp0
			setCel: 1
		)
		(super show: &rest)
	)
)

(instance colon of View
	(properties
		view 264
		loop 4
		cel 3
		priority 15
		signal $4010
	)
	
	(method (init)
		(self signal: 16400)
		(self x: (+ (pad x?) 15 22) y: (- (+ (pad y?) 27) 9))
		(super init: &rest)
	)
)

(instance pad of Prop
	(properties
		x 10
		y 85
		noun 5
		modNum 241
		view 264
		loop 14
		cel 3
		priority 14
		signal $4010
	)
	
	(method (init)
		(super init: &rest)
		(self show:)
		(d0 init:)
		(d1 init:)
		(d2 init:)
		(d3 init:)
		(tempKey init: hide:)
		(directions init: setOnMeCheck: 26505)
		(tempKey2 init: show:)
		(localproc_05a3 theRegister)
		(mouseDownHandler addToFront: self)
		(keyDownHandler addToFront: self)
	)
	
	(method (doit &tmp temp0)
		(super doit: &rest)
		(if (and (not script) local0 (not local12))
			(= temp0 (localproc_0083))
			(self setScript: keyFlash 0 temp0)
		)
	)
	
	(method (dispose)
		(d0 dispose:)
		(d1 dispose:)
		(d2 dispose:)
		(d3 dispose:)
		(colon dispose:)
		(tempKey dispose:)
		(directions dispose:)
		(tempKey2 dispose:)
		(keyDownHandler delete: self)
		(mouseDownHandler delete: self)
		(super dispose: &rest)
	)
	
	(method (handleEvent event &tmp eventMessage temp1 [temp2 20])
		(if (and (user controls?) (user input?))
			(cond 
				((== (theIconBar curIcon?) (theIconBar at: 1)) (super handleEvent: event &rest))
				(
					(and
						(not (& (event type?) evVERB))
						(!= (event message?) KEY_TAB)
						(not (self onMe: event))
						(not script)
						(not (event modifiers?))
					)
					(keyStuff cue:)
					(event claimed: 1)
					(return)
				)
				(
					(and
						(= eventMessage (event message?))
						(& (event type?) evKEYBOARD)
					)
					(cond 
						(
							(and
								(== eventMessage KEY_RETURN)
								(!= (= temp1 (localproc_0266 event)) -1)
							)
							(event claimed: 1)
							(localproc_006c temp1)
							(event claimed?)
							(return)
						)
						(
						(and (== eventMessage KEY_TAB) (not (proc241_3)))
							(event claimed: 1)
							(proc241_2 (mod (+ (proc241_2) 1) 3))
							(tempKey2 show:)
							(event claimed?)
							(return)
						)
						(
						(and (<= KEY_0 eventMessage) (<= eventMessage KEY_9))
							(event claimed: 1)
							(localproc_006c (- (event message?) KEY_0))
							(event claimed?)
							(return)
						)
						((OneOf eventMessage 27 67 99)
							(event claimed: 1)
							(localproc_006c 11)
							(event claimed?)
							(return)
						)
						((OneOf eventMessage 69 101)
							(event claimed: 1)
							(localproc_006c 14)
							(event claimed?)
							(return)
						)
						(else (super handleEvent: event &rest))
					)
				)
				(
					(and
						(& (event type?) evMOUSEBUTTON)
						(!= (= temp1 (localproc_0266 event)) -1)
						(not (event modifiers?))
					)
					(event claimed: 1)
					(localproc_006c temp1)
					(event claimed?)
					(return)
				)
				(else (super handleEvent: event &rest))
			)
		else
			(super handleEvent: event &rest)
		)
	)
	
	(method (hide)
		(d0 hide:)
		(d1 hide:)
		(d2 hide:)
		(d3 hide:)
		(colon hide:)
		(tempKey hide:)
		(tempKey2 hide:)
		(super hide: &rest)
	)
)

(instance keyFlash of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(theMusic2 number: 124 loop: 1 play:)
				(if (and (< 9 register) (< register 13))
					(tempKey2 startUpd: show: register)
				else
					(tempKey startUpd: show: register)
				)
				(tempKey startUpd: show: register)
				(= ticks 5)
			)
			(1
				(localproc_009d register)
				(= ticks 5)
			)
			(2
				(switch register
					(13
						(self setScript: clearScript self)
					)
					(14
						(if
						(or (d0 loop?) (d1 loop?) (d2 loop?) (d3 loop?))
							(proc241_1 (localproc_062a))
						)
						(if (== (tempKey2 loop?) 10)
							(proc241_4 471)
						else
							(proc241_4 472)
						)
						(= cycles 1)
					)
					(else  (= cycles 1))
				)
			)
			(3
				(d0 stopUpd:)
				(d1 stopUpd:)
				(d2 stopUpd:)
				(d3 stopUpd:)
				(tempKey hide:)
				(theMusic2 stop:)
				(self dispose:)
			)
		)
	)
)

(instance keyStuff of Script
	(properties)
	
	(method (init param1 param2 param3 param4 param5)
		(if (> argc 3)
			(pad x: param4)
			(if (> argc 4) (pad y: param5))
		)
		(super init: param1 param2 param3 &rest)
	)
	
	(method (changeState newState &tmp [temp0 20])
		(switch (= state newState)
			(0
				(= theRegister register)
				(pad init:)
				(theGame handsOn:)
				(theIconBar disable: 0 3 5 4 6)
				(theGame
					setCursor: 982 1 (+ (pad x?) 10) (+ (pad y?) 10)
				)
				(theIconBar select: (theIconBar at: 2))
			)
			(1
				(theGame handsOff:)
				(pad hide:)
				(if (> (d0 loop?) 9) (d0 loop: 0))
				(if (> (d1 loop?) 9) (d1 loop: 0))
				(if (> (d2 loop?) 9) (d2 loop: 0))
				(if (> (d3 loop?) 9) (d3 loop: 0))
				(= cycles 6)
			)
			(2
				(pad dispose: delete:)
				(self dispose:)
			)
		)
	)
)

(instance clearScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(curRoom setScript: 0)
				(= cycles 1)
			)
			(1
				(d0 startUpd: loop: 0)
				(d1 startUpd: loop: 0)
				(d2 startUpd: loop: 0)
				(d3 startUpd: loop: 0)
				(proc241_3 0)
				(= theRegister 0)
				(localproc_05a3 0)
				(colon dispose:)
				(= cycles 1)
			)
			(2 (self dispose:))
		)
	)
)

(instance directions of Feature
	(properties)
	
	(method (init)
		(self
			nsLeft: (+ (pad x?) 70)
			nsTop: (+ (pad y?) 53)
			nsRight: (+ (pad x?) 114)
			nsBottom: (+ (pad y?) 92)
			y: 200
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(Print
					addText: 2 1 0 1 0 0 241
					addText: 2 1 14 1 0 12 241
					addText: 2 1 15 1 130 12 241
					addText: 2 1 14 2 0 24 241
					addText: 2 1 15 2 130 24 241
				)
				(Print
					addText: 2 1 14 3 0 36 241
					addText: 2 1 15 3 130 36 241
					addText: 2 1 14 4 0 48 241
					addText: 2 1 15 4 130 48 241
					addText: 2 1 14 5 0 60 241
					addText: 2 1 15 5 130 60 241
				)
				(Print
					addText: 2 1 14 6 0 72 241
					addText: 2 1 15 6 130 72 241
					addText: 2 1 14 7 0 84 241
					addText: 2 1 15 7 130 84 241
					addText: 2 1 14 8 0 96 241
					addText: 2 1 15 8 130 96 241
				)
				(Print
					addText: 2 1 14 9 0 108 241
					addText: 2 1 15 9 130 108 241
					addText: 2 1 14 10 0 120 241
					addText: 2 1 15 10 130 120 241
					init:
				)
			)
		)
	)
)
