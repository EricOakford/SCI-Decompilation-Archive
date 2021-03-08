;;; Sierra Script 1.0 - (do not remove this comment)
(script# 203)
(include sci.sh)
(use Main)
(use Inset)
(use Sound)
(use User)
(use Actor)
(use System)

(public
	keyStuff 0
)

(local
	local0
	[local1 11]
	local12
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
		(d4 startUpd: setLoop: (d3 loop?))
		(d3 startUpd: setLoop: (d2 loop?))
		(d2 startUpd: setLoop: (d1 loop?))
		(d1 startUpd: setLoop: (d0 loop?))
		(d0 startUpd: setLoop: param1)
	)
)

(procedure (localproc_010c param1)
	(return
		(cond 
			(
				(InRect
					(+ (pad x?) 13)
					(+ (pad y?) 26)
					(+ (pad x?) 70)
					(+ (pad y?) 74)
					param1
				)
				(return
					(+
						(* (/ (- (param1 y?) (+ (pad y?) 26)) 16) 3)
						1
						(/ (- (param1 x?) (+ (pad x?) 13)) 19)
					)
				)
			)
			(
				(InRect
					(+ (pad x?) 13)
					(+ (pad y?) 74)
					(+ (pad x?) 32)
					(+ (pad y?) 90)
					param1
				)
				(return 10)
			)
			(
				(InRect
					(+ (pad x?) 51)
					(+ (pad y?) 74)
					(+ (pad x?) 70)
					(+ (pad y?) 90)
					param1
				)
				(return 11)
			)
			(
				(InRect
					(+ (pad x?) 24)
					(+ (pad y?) 14)
					(+ (pad x?) 57)
					(+ (pad y?) 24)
					param1
				)
				(return 12)
			)
			(
				(InRect
					(+ (pad x?) 32)
					(+ (pad y?) 74)
					(+ (pad x?) 51)
					(+ (pad y?) 90)
					param1
				)
				(return 0)
			)
			(else (return -1))
		)
	)
)

(procedure (localproc_028e param1)
	(switch param1
		(0 19)
		(10 0)
		(11 38)
		(12 11)
		(else 
			(* (mod (- param1 1) 3) 19)
		)
	)
)

(procedure (localproc_02c7 param1)
	(switch param1
		(0 48)
		(10 48)
		(11 48)
		(12 -12)
		(else 
			(* (/ (- param1 1) 3) 16)
		)
	)
)

(instance d0 of Prop
	(properties
		view 992
		loop 10
		priority 15
		signal $0010
	)
	
	(method (init)
		(self posn: (+ 14 (pad x?) 48) (+ 2 (pad y?)))
		(super init: &rest)
	)
)

(instance d1 of Prop
	(properties
		view 992
		loop 10
		priority 15
		signal $0010
	)
	
	(method (init)
		(self posn: (+ 14 (pad x?) 36) (+ 2 (pad y?)))
		(super init: &rest)
	)
)

(instance d2 of Prop
	(properties
		view 992
		loop 10
		priority 15
		signal $0010
	)
	
	(method (init)
		(self posn: (+ 14 (pad x?) 24) (+ 2 (pad y?)))
		(super init: &rest)
	)
)

(instance d3 of Prop
	(properties
		view 992
		loop 10
		priority 15
		signal $0010
	)
	
	(method (init)
		(self posn: (+ 14 (pad x?) 12) (+ 2 (pad y?)))
		(super init: &rest)
	)
)

(instance d4 of Prop
	(properties
		view 992
		loop 14
		priority 15
		signal $0010
	)
	
	(method (init)
		(if (== curRoomNum 460) (self loop: 15))
		(self posn: (+ 14 (pad x?) 0) (+ 2 (pad y?)))
		(super init: &rest)
	)
)

(instance tempKey of Prop
	(properties
		view 992
		cel 1
		priority 15
		signal $4010
	)
	
	(method (init)
		(if (== curRoomNum 460) (self view: 461))
		(super init: &rest)
	)
	
	(method (show param1)
		(self
			posn:
				(+ (localproc_028e param1) 13 (pad x?))
				(+ (localproc_02c7 param1) 26 (pad y?))
			setLoop: param1
			setCel: 1
		)
		(soundFX loop: 1 play:)
		(super show: &rest)
	)
)

(instance pad of Inset
	(properties
		view 992
		loop 13
		x 196
		y 45
	)
	
	(method (init)
		(if (== curRoomNum 460)
			(self view: 461 loop: 13 cel: 0 x 196 y: 45)
		)
		(super init: &rest)
		(d1 init:)
		(d2 init:)
		(d3 init:)
		(d4 init:)
		(d0 init:)
		(tempKey init: hide:)
	)
	
	(method (doit &tmp temp0)
		(super doit: &rest)
		(if (and (not script) local0 (not local12))
			(= temp0 (localproc_0083))
			(self setScript: keyFlash 0 temp0)
		)
	)
	
	(method (dispose)
		(soundFX dispose:)
		(super dispose: &rest)
	)
	
	(method (handleEvent event &tmp temp0 temp1)
		(if (User controls?)
			(cond 
				(
					(and
						(= temp0 (event message?))
						(& (event type?) evKEYBOARD)
					)
					(cond 
						(
							(and
								(== temp0 13)
								(!= (= temp1 (localproc_010c event)) -1)
							)
							(localproc_006c temp1)
							(event claimed: 1)
						)
						((and (<= 48 temp0) (<= temp0 57))
							(event claimed: 1)
							(localproc_006c (- (event message?) KEY_0))
						)
						((OneOf temp0 27 67 99) (event claimed: 1) (localproc_006c 11))
						((OneOf temp0 69 101) (event claimed: 1) (localproc_006c 10))
						(else (super handleEvent: event &rest))
					)
				)
				(
					(and
						(& (event type?) evMOUSEBUTTON)
						(!= (= temp1 (localproc_010c event)) -1)
						(not (event modifiers?))
					)
					(event claimed: 1)
					(localproc_006c temp1)
				)
				(else (super handleEvent: event &rest))
			)
		else
			(super handleEvent: event &rest)
		)
	)
)

(instance keyFlash of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(tempKey startUpd: show: register)
				(= ticks 5)
			)
			(1
				(localproc_009d register)
				(= ticks 5)
			)
			(2
				(switch register
					(12
						(self setScript: clearScript self)
					)
					(11
						(self setScript: escScript self)
					)
					(10
						(self setScript: enterScript self)
					)
					(else  (= cycles 1))
				)
				(= ticks 5)
			)
			(3
				(d0 stopUpd:)
				(d1 stopUpd:)
				(d2 stopUpd:)
				(d3 stopUpd:)
				(d4 stopUpd:)
				(tempKey hide:)
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
	
	(method (dispose)
		(super dispose: &rest)
		(DisposeScript 203)
	)
	
	(method (changeState newState &tmp [temp0 20])
		(switch (= state newState)
			(0
				(theGame handsOn:)
				(if (== curRoomNum 460)
					(theIconBar disable: 0 1 3 5 4 7)
				else
					(theIconBar disable: 0 1 3 5 4 6)
				)
				(= register -1)
				(= cycles 1)
			)
			(1
				(cast eachElementDo: #stopUpd)
				(curRoom setInset: pad self)
				(theGame
					setCursor: 982 1 (+ (pad x?) 22) (+ (pad y?) 27)
				)
			)
			(2
				(cast eachElementDo: #startUpd)
				(if (!= curRoomNum 460)
					(theIconBar enable: 0 1 3 5 4 6)
				)
				(if (> (d0 loop?) 9) (d0 loop: 0))
				(if (> (d1 loop?) 9) (d1 loop: 0))
				(if (> (d2 loop?) 9) (d2 loop: 0))
				(if (> (d3 loop?) 9) (d3 loop: 0))
				(if (> (d4 loop?) 9) (d4 loop: 0))
				(= ticks 6)
			)
			(3
				(curRoom
					notify:
						register
						(d4 loop?)
						(d3 loop?)
						(d2 loop?)
						(d1 loop?)
						(d0 loop?)
				)
				(= cycles 1)
			)
			(4 (self dispose:))
		)
	)
)

(instance enterScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOn:)
				(theGame handsOff:)
				(keyStuff register: 1)
				(= cycles 1)
			)
			(1
				(pad dispose:)
				(self dispose:)
			)
		)
	)
)

(instance escScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(pad dispose:)
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
				(d0 startUpd: setLoop: 10)
				(d1 startUpd: setLoop: 10)
				(d2 startUpd: setLoop: 10)
				(d3 startUpd: setLoop: 10)
				(d4
					startUpd:
					setLoop: (if (== curRoomNum 460) 15 else 14)
				)
				(= cycles 3)
			)
			(1 (self dispose:))
		)
	)
)

(instance soundFX of Sound
	(properties
		number 124
	)
)
