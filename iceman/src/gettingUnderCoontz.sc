;;; Sierra Script 1.0 - (do not remove this comment)
(script# 341)
(include game.sh)
(use Main)
(use Intrface)
(use n396)
(use Submarine_806)
(use EgoDead)
(use Motion)
(use Actor)
(use System)

(public
	gettingUnderCoontz 0
)

(local
	local0
	local1
	local2
)
(procedure (localproc_05fa &tmp temp0 theX theY submarineAbsHeading)
	(return
		(if
			(and
				(< (Abs (- (coontz x?) (ourSub x?))) 3)
				(< (Abs (- (coontz y?) (ourSub y?))) 3)
			)
			(return 1)
		else
			(= temp0 (/ (+ (Submarine hSpeed?) 19) 20))
			(if (< (Submarine hSpeed?) 0) (= temp0 -1))
			(cond 
				(
				(< (= submarineAbsHeading (Submarine absHeading:)) 30) (= theX 0) (= theY (- temp0)))
				(
					(and
						(<= 30 submarineAbsHeading)
						(<= submarineAbsHeading 60)
					)
					(= theX temp0)
					(= theY (- temp0))
				)
				(
					(and
						(<= 60 submarineAbsHeading)
						(<= submarineAbsHeading 120)
					)
					(= theX temp0)
					(= theY 0)
				)
				(
					(and
						(<= 120 submarineAbsHeading)
						(<= submarineAbsHeading 150)
					)
					(= theX temp0)
					(= theY temp0)
				)
				(
					(and
						(<= 150 submarineAbsHeading)
						(<= submarineAbsHeading 210)
					)
					(= theX 0)
					(= theY temp0)
				)
				(
					(and
						(<= 210 submarineAbsHeading)
						(<= submarineAbsHeading 240)
					)
					(= theX (- temp0))
					(= theY temp0)
				)
				(
					(and
						(<= 240 submarineAbsHeading)
						(<= submarineAbsHeading 300)
					)
					(= theX (- temp0))
					(= theY 0)
				)
				(
					(and
						(<= 300 submarineAbsHeading)
						(<= submarineAbsHeading 330)
					)
					(= theX (- temp0))
					(= theY (- temp0))
				)
				(else (= theX 0) (= theY (- temp0)))
			)
			(+= theX (ourSub x?))
			(+= theY (ourSub y?))
			(if
				(and
					(<= 112 theX)
					(<= theX 202)
					(<= 35 theY)
					(<= theY 99)
				)
				(ourSub show: x: theX y: theY)
			else
				(ourSub hide: x: theX y: theY)
			)
			(return 0)
		)
	)
)

(procedure (localproc_07b3)
	(return
		(if local2
			(return (not (InRect 117 35 198 97 ourSub)))
		else
			(return 0)
		)
	)
)

(instance gettingUnderCoontz of Script

	(method (doit &tmp temp0)
		(if (and (not (umod (++ local1) 20)) (localproc_05fa))
			(if
			(and (<= (Submarine depth:) 150) (not script))
				(self setScript: diedUnderCoontz)
			else
				(= temp0
					(cond 
						((< 2400 local0) 5)
						((< 1800 local0) 4)
						((< 1200 local0) 3)
						((< 600 local0) 2)
						(else 1)
					)
				)
				(Printf 341 3 temp0)
				(theGame changeScore: temp0)
				(= local2 0)
				(ourSub setPri: 0 dispose:)
				(coontz setPri: 0 dispose:)
				(Animate (cast elements?) FALSE)
				(self dispose:)
			)
		)
		(if (and (<= (Submarine depth:) 70) (not script))
			(self setScript: showedSelf)
		)
		(if (and (not (-- local0)) (not script))
			(self setScript: didntGetUnderCoontz_a)
		)
		(if (and (not script) (localproc_07b3))
			(self setScript: didntGetUnderCoontz_a)
		)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
		(cls)
		(DisposeScript 396)
		(DisposeScript 341)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local0 3000)
				(coontz init: setLoop: 2 moveSpeed: 20 setCycle: Forward)
				(ourSub init: setLoop: 3 setCycle: Forward)
				(= local2 1)
				(= cycles 2)
			)
			(1
				(= start state)
				(coontz setMotion: MoveTo 160 40 self)
			)
			(2
				(coontz setMotion: MoveTo 140 60 self)
			)
			(3
				(coontz setMotion: MoveTo 165 70 self)
			)
			(4
				(coontz setMotion: MoveTo 180 58 self)
			)
			(5
				(coontz setMotion: MoveTo 180 50 self)
			)
			(6 (self init:))
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((== (event type?) 1024)
				(switch (event message?)
					(0
						(SubPrint 5 341 0)
						(self setScript: firedOnCoontz)
					)
					(1
						(SubPrint 5 341 1)
					)
					(2
						(SubPrint 5 341 2)
						(self setScript: firedOnCoontz)
					)
				)
			)
		)
	)
)

(instance didntGetUnderCoontz_a of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(coontz hide: forceUpd: dispose: delete:)
				(SubPrint 4 341 4)
				(= seconds 5)
			)
			(1 (EgoDead 7 0 0 341 5))
		)
	)
)

(instance didntGetUnderCoontz_b of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(coontz hide: forceUpd: dispose: delete:)
				(SubPrint 4 341 4)
				(= seconds 5)
			)
			(1 (EgoDead 7 0 0 341 6))
		)
	)
)

(instance showedSelf of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(coontz hide: forceUpd: dispose: delete:)
				(SubPrint 4 341 4)
				(= seconds 5)
			)
			(1 (EgoDead 7 0 0 341 7))
		)
	)
)

(instance firedOnCoontz of Script

	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 4))
			(1 (EgoDead 926 1 0 341 8))
		)
	)
)

(instance diedUnderCoontz of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(SubPrint 4 341 9)
				(= seconds 5)
			)
			(1 (EgoDead 7 0 0 341 10))
		)
	)
)

(instance coontz of Actor
	(properties
		y 50
		x 180
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
		y 65
		x 135
		view 40
		loop 3
		priority 14
		signal (| ignrAct fixPriOn)
	)
)
