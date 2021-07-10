;;; Sierra Script 1.0 - (do not remove this comment)
(script# 340)
(include game.sh)
(use Main)
(use Intrface)
(use subMarine)
(use n396)
(use Submarine_806)
(use EgoDead)
(use Motion)
(use Actor)
(use System)

(public
	trackingTheCoontz 0
)

(local
	local0
	local1
	local2
	local3
)
(procedure (localproc_0748 &tmp temp0 temp1 temp2 submarineAbsHeading temp4 temp5 temp6)
	(= temp5 (Abs (- (coontz x?) (ourSub x?))))
	(= temp6 (Abs (- (coontz y?) (ourSub y?))))
	(if (< local1 15000)
		(= local1 (+ local1 (Max temp5 temp6)))
		(++ local2)
	)
	(return
		(if (and (< temp5 12) (< temp6 12))
			(= temp0 (/ (+ (Submarine hSpeed?) 19) 20))
			(if (< (Submarine hSpeed?) 0) (= temp0 -1))
			(cond 
				(
				(< (= submarineAbsHeading (Submarine absHeading:)) 30) (= temp1 0) (= temp2 (- temp0)))
				(
					(and
						(<= 30 submarineAbsHeading)
						(<= submarineAbsHeading 60)
					)
					(= temp1 temp0)
					(= temp2 (- temp0))
				)
				(
					(and
						(<= 60 submarineAbsHeading)
						(<= submarineAbsHeading 120)
					)
					(= temp1 temp0)
					(= temp2 0)
				)
				(
					(and
						(<= 120 submarineAbsHeading)
						(<= submarineAbsHeading 150)
					)
					(= temp1 temp0)
					(= temp2 temp0)
				)
				(
					(and
						(<= 150 submarineAbsHeading)
						(<= submarineAbsHeading 210)
					)
					(= temp1 0)
					(= temp2 temp0)
				)
				(
					(and
						(<= 210 submarineAbsHeading)
						(<= submarineAbsHeading 240)
					)
					(= temp1 (- temp0))
					(= temp2 temp0)
				)
				(
					(and
						(<= 240 submarineAbsHeading)
						(<= submarineAbsHeading 300)
					)
					(= temp1 (- temp0))
					(= temp2 0)
				)
				(
					(and
						(<= 300 submarineAbsHeading)
						(<= submarineAbsHeading 330)
					)
					(= temp1 (- temp0))
					(= temp2 (- temp0))
				)
				(else (= temp1 0) (= temp2 (- temp0)))
			)
			(= temp1 (+ temp1 (ourSub x?)))
			(= temp2 (+ temp2 (ourSub y?)))
			(if
				(and
					(<= 112 temp1)
					(<= temp1 202)
					(<= 35 temp2)
					(<= temp2 99)
				)
				(ourSub show: x: temp1 y: temp2)
			else
				(ourSub hide: x: temp1 y: temp2)
			)
			(return 0)
		else
			(return 1)
		)
	)
)

(procedure (localproc_0922)
	(return
		(if local3
			(return (not (InRect 117 35 198 97 ourSub)))
		else
			(return 0)
		)
	)
)

(instance trackingTheCoontz of Script

	(method (doit)
		(if
			(and
				(< state 6)
				(not (umod (++ local0) 20))
				(localproc_0748)
				(not script)
			)
			(self setScript: strayedAway)
		)
		(if
			(and
				(not script)
				(< state 8)
				(< (Submarine depth:) 150)
			)
			(self setScript: hitCoontz)
		)
		(if
			(and
				(== 9 state)
				(<= (Submarine depth:) 70)
				(<= (Submarine hSpeed?) 5)
			)
			(self cue: 1)
		)
		(if (and (not script) (localproc_0922))
			(self setScript: strayedAway)
		)
		(super doit:)
	)
	
	(method (dispose)
		(cond 
			((< state 10)
				(coontz hide: forceUpd: dispose: delete:)
				(EgoDead 7 0 0 340 0)
			)
			((< 1 (Submarine throttle?)) (self setScript: tooLong 0 0))
			((!= (Submarine pitch?) 0) (self setScript: tooLong 0 1))
			(else
				(super dispose:)
				(cls)
				(DisposeScript 340)
				(DisposeScript 396)
			)
		)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(coontz init: setLoop: 2 moveSpeed: 20 setCycle: Forward)
				(ourSub init: setLoop: 3 setCycle: Forward)
				(SubPrint 5 340 4)
				(= seconds 6)
			)
			(1
				(SubPrint 5 340 5)
				(coontz setMotion: MoveTo 128 70 self)
			)
			(2
				(coontz setMotion: MoveTo 150 48 self)
			)
			(3
				(coontz xStep: 2 yStep: 2 setMotion: MoveTo 180 48 self)
			)
			(4
				(Submarine longitude: -12 latitude: 35)
				(coontz xStep: 1 yStep: 1 setMotion: MoveTo 190 40 self)
			)
			(5
				(coontz setMotion: MoveTo 193 40 self)
			)
			(6
				(Submarine longitude: -13 latitude: 33)
				(SubPrint 4 340 6)
				(= seconds 3)
			)
			(7
				(SubPrint 4 340 7)
				(= local3 0)
				(coontz hide: forceUpd: dispose: delete:)
				(ourSub hide: forceUpd: dispose: delete:)
				(= seconds 3)
			)
			(8
				(= local1 (/ local1 local2))
				(= temp0
					(*
						2
						(cond 
							((< local1 3) 5)
							((< local1 5) 4)
							((< local1 7) 3)
							((< local1 9) 2)
							(else 1)
						)
					)
				)
				(Printf 340 8 temp0)
				(theGame changeScore: temp0)
				(Print 340 9)
				(Print 340 10)
				(Print 340 11)
				(Print 340 12)
				(subMarine roomFlags: (& (subMarine roomFlags?) $fffd))
				(subMarine cue:)
				(= cycles 2)
			)
			(9 (= cycles 2000))
			(10
				(if (>= argc 2)
					(Print 340 13)
					(= seconds 30)
				else
					(self setScript: tooLong 0 2)
				)
			)
			(11
				(self setScript: tooLong 0 3)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((== (event type?) 1024)
				(switch (event message?)
					(0
						(SubPrint 5 340 1)
						(self setScript: firedOnCoontz)
					)
					(1
						(SubPrint 5 340 2)
					)
					(2
						(SubPrint 5 340 3)
						(self setScript: firedOnCoontz)
					)
				)
			)
		)
	)
)

(instance strayedAway of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(coontz hide: forceUpd: dispose: delete:)
				(SubPrint 4 340 14)
				(= seconds 5)
			)
			(1 (EgoDead 7 0 0 340 15))
		)
	)
)

(instance hitCoontz of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(coontz hide: forceUpd: dispose: delete:)
				(SubPrint 4 340 16)
				(= seconds 5)
			)
			(1 (EgoDead 7 0 0 340 17))
		)
	)
)

(instance tooLong of Script

	(method (changeState)
		(coontz hide: forceUpd: dispose: delete:)
		(switch register
			(0 (EgoDead 7 0 0 340 18))
			(1 (EgoDead 7 0 0 340 19))
			(2 (EgoDead 7 0 0 340 18))
			(3 (EgoDead 7 0 0 340 20))
		)
	)
)

(instance firedOnCoontz of Script

	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 4))
			(1 (EgoDead 926 1 0 340 21))
		)
	)
)

(instance coontz of Actor
	(properties
		y 88
		x 128
		yStep 1
		view 40
		loop 2
		priority 14
		signal (| ignrAct fixPriOn)
		illegalBits $0000
		xStep 1
	)
)

(instance ourSub of Prop
	(properties
		y 88
		x 128
		view 40
		loop 3
		priority 14
		signal (| ignrAct fixPriOn)
	)
)
