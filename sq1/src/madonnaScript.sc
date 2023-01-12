;;; Sierra Script 1.0 - (do not remove this comment)
(script# MADONNA) ;301
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Actor)
(use System)

(public
	madonnaScript 0
	madonna 1
	madonnaFace 2
)

(instance madonnaScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(madonna setLoop: 0 cel: 0 setCycle: Forward)
				(= seconds (Random 3 5))
			)
			(1 (madonna setCycle: EndLoop self))
			(2
				(if (!= (madonna loop?) 6)
					(madonna
						setLoop: (+ (madonna loop?) 1)
						cel: 0
						setCycle: EndLoop self
					)
				else
					(self changeState: 0)
				)
			)
			(3
				(theMusic send: 6 78 0)
				(switch (madonna loop?)
					(1
						(madonnaFace posn: 140 52 setLoop: 7)
					)
					(2
						(madonnaFace posn: 147 60 setLoop: 8)
					)
					(3
						(madonnaFace posn: 140 50 setLoop: 7)
					)
					(4
						(madonnaFace posn: 135 53 setLoop: 8)
					)
					(5
						(madonnaFace posn: 130 60 setLoop: 7)
					)
					(6
						(madonnaFace posn: 140 50 setLoop: 7)
					)
				)
				(madonnaFace show:)
				(= cycles (Random 90 120))
			)
			(4
				(theMusic send: 6 78 1)
				(madonnaFace hide:)
				(= cycles (= state 1))
			)
		)
	)
)

(instance madonna of Prop
	(properties
		x 138
		y 91
		description {humanoid}
		lookStr {On stage, a strangely attired woman performs her act (if that's what you want to call it). 
		You haven't had a girlfriend for a long time (more like forever), but even that's not enough to make her attractive.}
		view 435
		signal (| ignrAct fixedLoop)
		cycleSpeed 4
		detailLevel 2
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(Print 301 0)
			)
			(verbTaste
				(Print 301 1)
			)
			(verbSmell
				(Print 301 2)
			)
			(verbTalk
				(Print 301 3)
			)
			(verbUse
				(switch theItem
					(iBuckazoid
						(Print 301 4)
					)
					(iCartridge
						(Print 301 5)
					)
					(iWidget
						(Print 301 6)
					)
					(iGadget
						(Print 301 7)
					)
					(iKnife
						(Print 301 8)
					)
					(iDehydratedWater
						(Print 301 9)
					)
					(iJetpack
						(Print 301 10)
					)
					(iBarCoupon
						(Print 301 11)
					)
					(iDroidsBUsCoupon
						(Print 301 11)
					)
					(else 
						(super doVerb: theVerb theItem)
					)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance madonnaFace of Prop
	(properties
		x 142
		y 91
		description {humanoid}
		lookStr {On stage, a strangely attired woman performs her act (if that's what you want to call it). 
		You haven't had a girlfriend for a long time (more like forever), but even that's not enough to make her attractive.}
		view 435
		loop 7
		priority 6
		signal (| ignrAct fixedLoop fixPriOn)
		cycleSpeed 4
	)
	
	(method (doVerb theVerb)
		(madonna doVerb: theVerb &rest)
	)
)
