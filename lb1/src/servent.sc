;;; Sierra Script 1.0 - (do not remove this comment)
(script# 204)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Actor)


(class servent of Actor
	(properties
		guest1 0
		exitX 0
		exitY 0
		seconds 0
		lastSeconds 0
		serveState 0
		guestState 0
		serving 0
		outOfRoom 0
		itemX 0
		itemY 0
	)
	
	(method (init)
		(super init:)
		(Load FONT 4)
		(= serveState -1)
		(= guestState 1)
		(= serving 0)
		(|= global195 $0400)
	)
	
	(method (doit &tmp oldSeconds)
		(super doit:)
		(if
			(and
				seconds
				(!= lastSeconds (= oldSeconds (GetTime SYSTIME1)))
			)
			(= lastSeconds oldSeconds)
			(if (not (-- seconds))
				(self cue:)
			)
		)
		(if (and (== global167 1) (== serving 0))
			(self serve:)
			(= serving TRUE)
		)
	)
	
	(method (dispose)
		(DisposeScript AVOIDER)
		(= global195 (& global195 $fbff))
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0)
		(if (event claimed?) (return))
		(if
			(and
				(== (event type?) saidEvent)
				(self inRect: 0 0 319 191)
			)
			(cond 
				(
					(or
						(MousedOn self event shiftDown)
						(Said 'examine/butler,fellow')
					)
					(if (& global207 $0400)
						(Print 204 0)
					else
						(= theTalker talkJEEVES)
						(|= global207 $0400)
						(Say 0 204 1)
					)
					(event claimed: TRUE)
				)
				((Said 'flirt//(butler,fellow)<with')
					(Print 204 2)
				)
				((Said 'ask,get/drink,glass')
					(Print 204 3)
				)
				((Said 'ask,tell[/butler]/*<about')
					(Print 204 4)
				)
				(
					(or
						(Said 'deliver,hold/*/butler')
						(Said 'deliver,hold/*<butler')
					)
					(if (and theInvItem haveInvItem)
						(Print 204 4)
					else
						(DontHave)
					)
				)
				((Said 'examine/people')
					(Printf 204 5
						(switch curRoomNum
							(32 {Wilbur})
							(36 {Gloria})
							(38 {Clarence})
							(48 {Rudy})
						)
					)
				)
				((Said 'examine/men')
					(Printf 204 5
						(switch curRoomNum
							(32 {Wilbur})
							(38 {Clarence})
							(48 {Rudy})
							(else
								(event claimed: FALSE)
							)
						)
					)
				)
				((Said '/butler,fellow>')
					(cond 
						((Said 'converse')
							(Print 204 4)
						)
						((Said 'get')
							(Print 204 6)
						)
						((Said 'kill')
							(Print 204 7)
						)
						((Said 'embrace')
							(Print 204 8)
						)
						((Said 'kiss')
							(Print 204 9)
						)
					)
				)
			)
		)
	)
	
	(method (cue)
		(switch (++ serveState)
			(0
				(self loop: 3)
				(Print 204 10 #at 160 10 #font 4 #draw #dispose)
				(self converse:)
			)
			(1
				(cls)
				(Print 204 11 #at 160 10 #font 4 #draw #dispose)
				(self converse:)
			)
			(2
				(cls)
				(= serveState -1)
				(self leave:)
			)
			(3
				(if (== outOfRoom TRUE)
					(self setAvoider: 0 stopUpd: dispose:)
				)
			)
		)
	)
	
	(method (serve)
		(self setMotion: MoveTo itemX itemY self)
	)
	
	(method (leave)
		(= serveState 2)
		(= outOfRoom TRUE)
		(self setMotion: MoveTo exitX exitY self)
	)
	
	(method (converse)
		(= seconds 3)
	)
)
