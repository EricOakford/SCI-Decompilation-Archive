;;; Sierra Script 1.0 - (do not remove this comment)
(script# BLUESBROS) ;300
(include game.sh)
(use Main)
(use Intrface)
(use Osc)
(use Motion)
(use Actor)
(use System)

(public
	bluesBrothers 0
	jakeBlue 1
	elwoodBlue 2
)

(instance bluesBrothers of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setLoop: register cel: 0 setCycle: Forward)
				(= cycles (Random 45 150))
			)
			(1 (client setCycle: EndLoop self))
			(2
				(client
					setLoop: (+ register 1)
					cel: 0
					setCycle: EndLoop self
				)
			)
			(3
				(if (not register)
					(music send: 5 78 0)
				else
					(music send: 4 78 0)
				)
				(client
					setLoop: (+ register 2)
					cel: 0
					setCycle: Oscillate (Random 2 15) self
				)
			)
			(4
				(if (not register)
					(music send: 5 78 1)
				else
					(music send: 4 78 1)
				)
				(= state -1)
				(client
					setLoop: (+ register 1)
					cel: (if (not register) 2 else 3)
					setCycle: BegLoop self
				)
			)
		)
	)
)

(instance jakeBlue of Prop
	(properties
		x 136
		y 95
		description {humanoids}
		lookStr {There are a couple of non-galactic looking humanoids cranking out some unfamiliar sounding tunes. They seem interested solely in the music they are performing.}
		view 434
		priority 6
		signal (| ignrAct fixedLoop fixPriOn)
		cycleSpeed 4
		detailLevel 2
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(Print 300 0)
			)
			(verbTaste
				(Print 300 1)
			)
			(verbSmell
				(Print 300 2)
			)
			(verbTalk
				(Print 300 3)
			)
			(verbUse
				(switch theItem
					(iBuckazoid
						(Print 300 4)
					)
					(iCartridge
						(Print 300 5)
					)
					(iWidget
						(Print 300 6)
					)
					(iGadget
						(Print 300 7)
					)
					(iKnife
						(Print 300 8)
					)
					(iDehydratedWater
						(Print 300 9)
					)
					(iJetpack
						(Print 300 10)
					)
					(iBarCoupon
						(Print 300 11)
					)
					(iDroidsBUsCoupon
						(Print 300 11)
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

(instance elwoodBlue of Prop
	(properties
		x 163
		y 90
		description {humanoids}
		lookStr {There are a couple of non-galactic looking humanoids cranking out some unfamiliar sounding tunes. 
		They seem interested solely in the music they are performing.}
		view 434
		loop 3
		priority 6
		signal (| ignrAct fixedLoop fixPriOn)
		cycleSpeed 4
		detailLevel 2
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(Print 300 12)
			)
			(verbTaste
				(Print 300 1)
			)
			(verbSmell
				(Print 300 2)
			)
			(verbTalk
				(Print 300 13)
			)
			(verbUse
				(switch theItem
					(iBuckazoid
						(Print 300 4)
					)
					(iCartridge
						(Print 300 5)
					)
					(iWidget
						(Print 300 6)
					)
					(iGadget
						(Print 300 7)
					)
					(iKnife
						(Print 300 8)
					)
					(iDehydratedWater
						(Print 300 9)
					)
					(iJetpack
						(Print 300 10)
					)
					(iBarCoupon
						(Print 300 11)
					)
					(iDroidsBUsCoupon
						(Print 300 11)
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
