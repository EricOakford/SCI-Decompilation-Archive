;;; Sierra Script 1.0 - (do not remove this comment)
(script# 204)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Actor)


(class servent of Act
	(properties
		y 0
		x 0
		z 0
		heading 0
		yStep 2
		view 0
		loop 0
		cel 0
		priority 0
		underBits 0
		signal $0000
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		cycleSpeed 0
		script 0
		cycler 0
		timer 0
		illegalBits $8000
		xLast 0
		yLast 0
		xStep 3
		moveSpeed 0
		blocks 0
		baseSetter 0
		mover 0
		looper 0
		viewer 0
		avoider 0
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
		(Load rsFONT 4)
		(= serveState -1)
		(= guestState 1)
		(= serving 0)
		(= global195 (| global195 $0400))
	)
	
	(method (doit &tmp theLastSeconds)
		(super doit:)
		(if
			(and
				seconds
				(!= lastSeconds (= theLastSeconds (GetTime 1)))
			)
			(= lastSeconds theLastSeconds)
			(if (not (-- seconds)) (self cue:))
		)
		(if (and (== global167 1) (== serving 0))
			(self serve:)
			(= serving 1)
		)
	)
	
	(method (dispose)
		(DisposeScript 985)
		(= global195 (& global195 $fbff))
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0)
		(if (event claimed?) (return))
		(if
			(and
				(== (event type?) evSAID)
				(self inRect: 0 0 319 191)
			)
			(cond 
				(
					(or
						(MousedOn self event 3)
						(Said 'examine/butler,fellow')
					)
					(if (& global207 $0400)
						(Print 204 0)
					else
						(= theTalker 11)
						(= global207 (| global207 $0400))
						(Say 0 204 1)
					)
					(event claimed: 1)
				)
				((Said 'flirt//(butler,fellow)<with') (Print 204 2))
				((Said 'ask,get/drink,glass') (Print 204 3))
				((Said 'ask,tell[/butler]/*<about') (Print 204 4))
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
					(Printf
						204
						5
						(switch curRoomNum
							(32 {Wilbur})
							(36 {Gloria})
							(38 {Clarence})
							(48 {Rudy})
						)
					)
				)
				((Said 'examine/men')
					(Printf
						204
						5
						(switch curRoomNum
							(32 {Wilbur})
							(38 {Clarence})
							(48 {Rudy})
							(else  (event claimed: 0))
						)
					)
				)
				((Said '/butler,fellow>')
					(cond 
						((Said 'converse') (Print 204 4))
						((Said 'get') (Print 204 6))
						((Said 'kill') (Print 204 7))
						((Said 'embrace') (Print 204 8))
						((Said 'kiss') (Print 204 9))
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
				(if (== outOfRoom 1)
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
		(= outOfRoom 1)
		(self setMotion: MoveTo exitX exitY self)
	)
	
	(method (converse)
		(= seconds 3)
	)
)
