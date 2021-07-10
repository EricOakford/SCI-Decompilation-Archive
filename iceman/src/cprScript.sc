;;; Sierra Script 1.0 - (do not remove this comment)
(script# 354)
(include game.sh)
(use Main)
(use volleyRm)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	cprScript 0
)

(procedure (GotAPoint)
	(theGame changeScore: 1)
)

(instance Doctor of Actor
	(properties
		y 124
		x 330
		view 303
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/doc,man]>')
				(cond 
					((Said 'look[<at]')
						(HighPrint 354 0)
					)
					((Said 'address')
						(HighPrint 354 1)
					)
					((Said 'thank')
						(HighPrint 354 2)
					)
				)
			)
		)
	)
)

(instance egoRescueViewer of Code

	(method (doit)
		(ego
			view: 403
			setLoop: -1
			loop:
				(switch (ego onControl: origin)
					(cLBLUE 8)
					(cLGREEN 7)
					(cLCYAN 6)
					(cLRED 5)
					(else 
						(if (>= (ego y?) 200) 8 else 4)
					)
				)
		)
	)
)

(instance cprScript of Script

	(method (dispose)
		(super dispose:)
		(DisposeScript 354)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(HighPrint 354 3)
				((ego looper?) dispose:)
				(ego
					setAvoider: 0
					posn: 239 240
					view: 403
					viewer: 0
					setLoop: 8
					setCycle: Forward
					setMotion: MoveTo 239 170 self
				)
				(HighPrint 354 4)
			)
			(1
				(ego
					setCycle: Walk
					setMotion: MoveTo 239 122 self
					viewer: (egoRescueViewer doit: yourself:)
				)
			)
			(2
				(ego
					viewer: 0
					view: 403
					setLoop: 3
					heading: 180
					setMotion: 0
				)
				((ScriptID 3 1) heading: 270 loop: 1 cel: 0)
				((ScriptID 3 6) heading: 270 loop: 0 cel: 5)
				(= cycles 2)
			)
			(3
				(HighPrint 354 5)
				(ego setLoop: 2 setCel: 0 setCycle: BegLoop)
				(User canInput: 1)
			)
			(4
				(User canInput: 0)
				(HighPrint 354 6)
				(ego setLoop: 9 setCel: 0 setCycle: Forward)
				(GotAPoint)
				(= seconds 2)
			)
			(5
				(ego setCycle: BegLoop)
				(User canInput: TRUE)
			)
			(6
				(User canInput: 0)
				(HighPrint 354 7)
				((ScriptID 3 1)
					view: 603
					xStep: 6
					setCycle: Walk
					setMotion: MoveTo 340 100
				)
				(HighPrint 354 8)
				(GotAPoint)
				(User canInput: TRUE)
			)
			(7
				(HighPrint 354 9)
				(HighPrint 354 10)
				(GotAPoint)
			)
			(8
				(HighPrint 354 11)
				(GotAPoint)
			)
			(9
				(ego setLoop: 0 setCel: 2 setCycle: EndLoop self)
				(HighPrint 354 12)
			)
			(10
				(ego setCycle: BegLoop)
				(HighPrint 354 13)
				(GotAPoint)
			)
			(11
				(User canInput: 0)
				(ego setLoop: 0 setCel: 0 setCycle: CycleTo 3 1)
				(= seconds 2)
			)
			(12
				(HighPrint 354 14)
				(ego setCycle: BegLoop)
				(GotAPoint)
				(User canInput: TRUE)
			)
			(13
				(User canInput: 0)
				(ego setLoop: 0 setCel: 0 setCycle: CycleTo 2 1)
				(= seconds 3)
			)
			(14
				(HighPrint 354 15)
				(ego setCycle: BegLoop)
				(GotAPoint)
				(User canInput: TRUE)
			)
			(15
				(User canInput: FALSE)
				(= cycles 10)
			)
			(16
				(GotAPoint)
				((ScriptID 3 1) dispose:)
				(curRoom notify: 2)
				(self dispose:)
			)
		)
	)
	
	(method (handleEvent event &tmp wrong)
		(= wrong 0)
		(cond 
			((super handleEvent: event))
			(
				(switch state
					(3
						(if
							(or
								(and (Said 'shake>') (Said 'holler'))
								(Said 'shake//holler')
							)
						else
							(Said 'shake<holler')
						)
					)
					(5
						(Said 'call[/help][/help]')
					)
					(6
						(Said 'open,clear,establish/airway,neck')
					)
					(7
						(if
							(or
								(and (Said 'look>') (Said 'listen>') (Said 'feel>'))
								(Said 'look//feel<listen>')
							)
							(Said '[/breath,victim,babe]')
						)
					)
					(8
						(if (Said 'give/breath<2')
						else
							(Said 'give/breath<good/2')
						)
					)
					(10
						(Said 'look,listen,feel[/breath,victim,babe]')
					)
					(12
						(Said 'check[/pulse][/pulse]')
					)
					(14
						(if (Said 'begin/compression') else (Said 'begin/cpr'))
					)
					(else
						(= wrong TRUE)
					)
				)
				(if (not wrong)
					(self cue:)
				)
			)
			(else
				(HighPrint 354 16)
				(event claimed: TRUE)
			)
		)
	)
)
