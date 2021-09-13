;;; Sierra Script 1.0 - (do not remove this comment)
(script# 26)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm26 0
)

(local
	local0
	aPurser
	aGate
	shipIsHere
	aWave
	aBird
	aPoop
	aPoop2
)
(instance rm26 of Room
	(properties
		horizon 5
		north 22
		east 27
		west 25
	)
	
	(method (init)
		(if (and (ego has: iCruiseTicket) (ego has: iOnklunk))
			(= shipIsHere TRUE)
			(curRoom drawPic: 126)
		else
			(curRoom drawPic: 26)
		)
		(Load VIEW 236)
		(super init:)
		(if shipIsHere
			(Load VIEW 237)
			(Load VIEW 7)
			(Load VIEW 3)
			((= aPurser (Prop new:))
				view: 237
				ignoreActors:
				setCel: 0
				setPri: 9
				posn: 196 146
				init:
				stopUpd:
			)
			((= aBird (Actor new:))
				view: 236
				setLoop: 2
				setCel: 0
				setPri: 15
				setStep: 8 6
				illegalBits: 0
				posn: 192 48
				ignoreActors:
				init:
				setScript: birdScript
			)
			((= aPoop (Actor new:))
				view: 236
				setLoop: 5
				setCel: 0
				setPri: 12
				setStep: 4 4
				illegalBits: 0
				ignoreActors:
				posn: 999 999
				init:
				hide:
			)
			((= aPoop2 (Actor new:))
				view: 236
				setLoop: 5
				setCel: 0
				setPri: 12
				setStep: 4 4
				illegalBits: 0
				ignoreActors:
				posn: 999 999
				init:
				hide:
			)
			((= aWave (Prop new:))
				view: 236
				setLoop: 0
				setPri: 10
				posn: 296 161
				setCycle: Forward
				cycleSpeed: 1
				isExtra: 1
				init:
			)
		)
		((= aGate (Actor new:))
			view: 236
			setLoop: 1
			illegalBits: 0
			ignoreActors:
			setPri: 10
			posn: 188 146
			setStep: 1 1
			stopUpd:
			init:
		)
		(NormalEgo)
		(ego posn: 1 150 init:)
		(self setRegions: CITY setScript: rm26Script)
	)
)

(instance rm26Script of Script
	(method (doit)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(HandsOff)
				(Print 26 14)
				(aPurser setCycle: Forward)
				(= seconds 3)
			)
			(2
				(Print 26 15)
				(aPurser setCel: 0 stopUpd:)
				(= seconds 3)
			)
			(3
				(Print 26 16)
				(aPurser setCycle: Forward)
				(= seconds 3)
			)
			(4
				(Print 26 17)
				(aPurser setCel: 0 stopUpd:)
				(User canControl: TRUE canInput: TRUE)
			)
			(5
				(HandsOff)
				(Print 26 18)
				(aPurser setCycle: Forward)
				(= seconds 3)
			)
			(6
				(Print 26 19)
				(Print 26 20 #icon 7 1 0)
				(Print 26 21)
				(Print 26 22 #icon 3 0 0)
				(Print 26 23)
				(Print 26 24)
				(theGame changeScore: 9)
				(aPurser setCel: 0 stopUpd:)
				(ego
					put: iCruiseTicket -1
					illegalBits: 0
					setMotion: MoveTo 181 150 self
				)
			)
			(7
				(ego loop: 3)
				(aGate setMotion: MoveTo 218 146 self)
			)
			(8
				(ego setPri: 7 setMotion: MoveTo 182 144 self)
			)
			(9
				(aGate setMotion: MoveTo 188 146)
				(ego setMotion: MoveTo 179 121 self)
			)
			(10
				(ego setMotion: MoveTo 254 121 self)
			)
			(11
				(ego setMotion: MoveTo 318 94 self)
			)
			(12
				(curRoom newRoom: 27)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if shipIsHere
			(cond 
				(
					(or
						(Said 'look,finger,give/passport,ticket')
						(Said 'look,finger,give/man,agent/passport,ticket')
						(Said 'look,finger,give/passport,ticket/man,agent')
					)
					(cond 
						((not (ego inRect: 175 144 211 158))
							(NotClose)
						)
						((ego has: iPassport)
							(self changeState: 5)
						)
						(else
							(self changeState: 1)
						)
					)
				)
				((Said '/craft,boat')
					(Print 26 0)
				)
				((Said '/carpet')
					(Print 26 1)
					(Print 26 2 #at -1 152)
				)
				((Said '/chain')
					(Print 26 3)
				)
				((Said 'look/door')
					(Print 26 4)
				)
				((Said 'look/man')
					(Print 26 5)
				)
				((Said 'call/man')
					(Print 26 6)
				)
			)
		)
		(if (or (Said '//door') (Said '/door'))
			(Print 26 7)
			(Print 26 8 #at -1 152)
		)
		(if (Said 'look>')
			(if (Said '/bird')
				(Print 26 9)
			)
			(if (Said '/lagoon,beach,fluid,lagoon')
				(Print 26 10)
			)
			(if (Said '/door')
				(Print 26 11)
			)
			(if (Said '[/airport]')
				(Print 26 12)
				(if shipIsHere
					(Print 26 13)
				)
			)
		)
	)
)

(instance birdScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds (Random 2 3))
			)
			(1
				(aBird setCycle: EndLoop self)
			)
			(2
				(aBird
					setLoop: 3
					setCycle: Forward
					setMotion: MoveTo -12 49 self
				)
			)
			(3
				(= seconds (Random 4 8))
			)
			(4
				(if (not (ego inRect: 103 151 226 167))
					(= seconds 3)
					(-- state)
				else
					(aBird
						posn: 333 65
						setLoop: 3
						setCycle: Forward
						setMotion: MoveTo (+ (ego x?) 20) 66 self
					)
				)
			)
			(5
				(aBird setMotion: MoveTo -12 66)
				(aPoop
					posn: (aBird x?) (aBird y?)
					setCel: 0
					show:
					setMotion: MoveTo (- (aBird x?) 24) (+ (ego y?) 8) self
				)
			)
			(6
				(aPoop setCycle: EndLoop self)
			)
			(7
				(aBird setMotion: MoveTo -12 66 self)
			)
			(8
				(= seconds 3)
			)
			(9
				(if (not (ego inRect: 103 151 226 167))
					(= seconds 3)
					(-- state)
				else
					(aBird
						posn: -12 21
						setLoop: 4
						setCycle: Forward
						setMotion: MoveTo (- (ego x?) 20) 22 self
					)
				)
			)
			(10
				(aBird setMotion: MoveTo 333 22 self)
				(aPoop2
					posn: (aBird x?) (aBird y?)
					show:
					setMotion: MoveTo (+ 14 (aBird x?)) (+ (ego y?) 3) self
				)
			)
			(11
				(aPoop2 setCycle: EndLoop)
			)
			(12
				(aBird dispose:)
			)
		)
	)
)
