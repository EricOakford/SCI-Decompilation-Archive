;;; Sierra Script 1.0 - (do not remove this comment)
(script# 216)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm216 0
)
(synonyms
	(box lid)
)

(local
	talkCount
	mailboxOpen
	[str 222]
)
(procedure (KalalauSays &tmp seconds)
	(Print @str
		#at 10 15
		#title {Kalalau says...}
		#width 160
		#time (= seconds (PrintDelay @str))
		#dispose
	)
	(return (+ 3 seconds))
)

(procedure (YouSay &tmp seconds)
	(Print @str
		#at 10 123
		#title {You say...}
		#width 160
		#time (= seconds (PrintDelay @str))
		#dispose
	)
	(return (+ 3 seconds))
)

(instance rm216 of Room
	(properties
		picture 216
		east 210
	)
	
	(method (init)
		(if (not (Btst fBrokeUp))
			(aKandBB init:)
		)
		(Load VIEW 217)
		(Load SOUND 217)
		(Load SOUND 218)
		(super init:)
		(aMailBox init: stopUpd:)
		(self setScript: RoomScript)
		(if (and (Btst fBrokeUp) (not (Btst fCredits216)))
			(Load VIEW 219)
			(aCredit1 init:)
			(aCredit2 init:)
		)
		(NormalEgo)
		(ego
			loop: 1
			posn: 318 166
			observeBlocks: blockFence1 blockFence2
			init:
		)
		(if (cast contains: aKandBB)
			(soundFX number: 216 loop: -1 play:)
		)
	)
)

(instance RoomScript of Script
	(method (doit)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not (Btst fBrokeUp))
					(= cycles 40)
				)
			)
			(1
				(HandsOff)
				(ego loop: 3)
				(Format @str 216 32)
				(= seconds (YouSay))
				(= cycles 0)
			)
			(2
				(Format @str 216 33)
				(= seconds (KalalauSays))
			)
			(3
				(Print 216 34 #at -1 144 #dispose #time 11)
				(= seconds 14)
			)
			(4
				(Format @str 216 35)
				(= seconds (YouSay))
			)
			(5
				(Format @str 216 36)
				(= seconds (KalalauSays))
			)
			(6
				(Format @str 216 37)
				(= seconds (YouSay))
			)
			(7
				(Format @str 216 38)
				(= seconds (KalalauSays))
			)
			(8
				(Format @str 216 39)
				(= seconds (YouSay))
			)
			(9
				(Format @str 216 40)
				(= seconds (KalalauSays))
			)
			(10
				(Bset fBrokeUp)
				(Print
					(Format @str 216 41
						(if (>= filthLevel 3) { lesbian} else {})
					)
					#dispose
					#time 28
				)
				(= seconds 31)
			)
			(11
				(HandsOn)
				(cls)
				(= seconds 0)
			)
			(12
				(HandsOff)
				(ego
					ignoreActors:
					illegalBits: 0
					setMotion: MoveTo 118 120 self
				)
			)
			(13
				(ego loop: 3)
				(= seconds 2)
			)
			(14
				(ego
					view: 217
					setLoop: 0
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
				(soundFX number: 217 loop: 1 play:)
			)
			(15
				(aMailBox
					setCel: (if (InRoom iCreditCard) 1 else 2)
					stopUpd:
				)
				(ego setCycle: BegLoop self)
			)
			(16
				(NormalEgo loopN)
				(= mailboxOpen TRUE)
				(if (cast contains: aKandBB)
					(soundFX number: 216 loop: -1 play:)
				)
			)
			(18
				(HandsOff)
				(ego
					ignoreActors:
					illegalBits: 0
					setMotion: MoveTo 118 120 self
				)
			)
			(19
				(ego loop: 3)
				(= seconds 2)
			)
			(20
				(ego
					view: 217
					setLoop: 0
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(21
				(aMailBox setCel: 0 stopUpd:)
				(ego setCycle: BegLoop self)
				(soundFX number: 218 loop: 1 play:)
			)
			(22
				(NormalEgo loopN)
				(= mailboxOpen FALSE)
				(if (cast contains: aKandBB)
					(soundFX number: 216 loop: -1 play:)
				)
			)
			(23
				(HandsOff)
				(ego
					ignoreActors:
					illegalBits: 0
					setMotion: MoveTo 118 120 self
				)
			)
			(24
				(ego loop: 3)
				(= seconds 2)
			)
			(25
				(ego
					cycleSpeed: 1
					view: 217
					setLoop: 0
					cel: 0
					setCycle: EndLoop self
				)
			)
			(26
				(aMailBox setCel: 2 stopUpd:)
				(ego setLoop: 1 cel: 0 setCycle: EndLoop self)
			)
			(27
				(= seconds 2)
			)
			(28
				(Print 216 42 #at 10 5 #width 290)
				(= cycles 22)
			)
			(29
				(ego view: 217 setLoop: 2 cel: 0 setCycle: EndLoop self)
			)
			(30
				(= cycles 20)
			)
			(31
				(Print 216 43 #at 10 5 #width 290)
				(ego setCycle: BegLoop self)
			)
			(32
				(ego setLoop: 1 setCel: 255 setCycle: BegLoop self)
			)
			(33
				(ego get: iCreditCard)
				(theGame changeScore: 20)
				(Print 216 44 #icon 217 3 0 #at -1 10)
				(= seconds 3)
			)
			(34
				(NormalEgo loopN)
			)
		)
	)
	
	(method (handleEvent event)
		(if
			(and
				(not (super handleEvent: event))
				(not (event claimed?))
				modelessDialog
				(== (event message?) ENTER)
				(== (event type?) keyDown)
			)
			(event claimed: TRUE)
			(cls)
			(self cue:)
		)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			((Said 'climb,jump/wall')
				(if playingAsPatti
					(Print 216 0)
				else
					(Print 216 1)
				)
			)
			((Said '/club,club')
				(if playingAsPatti
					(Print 216 2)
				else
					(Print 216 3)
				)
			)
			((Said 'get/box')
				(Print 216 4)
			)
			((Said 'open/door')
				(Print 216 5)
			)
			((Said 'pick,break/bolt,door,fence')
				(Print 216 6)
			)
			((Said 'climb/door')
				(if playingAsPatti
					(Print 216 7)
				else
					(Print 216 8)
				)
			)
			((Said 'unbolt/door')
				(Print 216 9)
			)
			((Said 'look<in/box')
				(cond 
					(playingAsPatti
						(Print 216 10)
					)
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					((not (& (ego onControl:) cBLUE))
						(NotClose)
					)
					((not mailboxOpen)
						(Print 216 11)
					)
					((InRoom iCreditCard)
						(Print 216 12)
					)
					(else
						(Print 216 13)
						(Print 216 14)
					)
				)
			)
			((Said 'open/box')
				(cond 
					(playingAsPatti
						(Print 216 10)
					)
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					((not (& (ego onControl:) cBLUE))
						(NotClose)
					)
					(mailboxOpen
						(ItIs)
					)
					(else
						(self changeState: 12)
					)
				)
			)
			((Said 'close/box')
				(cond 
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					((not (& (ego onControl:) cBLUE))
						(NotClose)
					)
					((not mailboxOpen)
						(ItIs)
					)
					(else
						(self changeState: 18)
					)
				)
			)
			((Said 'get/card,letter,letter,envelope')
				(cond 
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					((not (InRoom iCreditCard))
						(Print 216 15)
					)
					((not (& (ego onControl:) cBLUE))
						(NotClose)
					)
					((not mailboxOpen)
						(Print 216 16)
					)
					(else
						(self changeState: 23)
					)
				)
			)
			((Said 'tickle/finial')
				(Print 216 17)
			)
			((Said '/wall,fence>')
				(cond 
					((Said 'climb/')
						(Print 216 18)
					)
					((Said 'look/')
						(if playingAsPatti
							(Print 216 19)
						else
							(Print 216 20)
						)
					)
					(else
						(event claimed: TRUE)
						(Print 216 21)
					)
				)
			)
			((Said 'look>')
				(cond 
					(
						(and
							(InRoom iCreditCard)
							(Said '/letter,letter,envelope,card')
						)
						(Print 216 22)
					)
					((Said '/box')
						(if (& (ego onControl:) cBLUE)
							(Print 216 23)
						else
							(Print 216 24)
						)
					)
					((Said '/door')
						(Print 216 25)
					)
					((Said '/finial')
						(Print 216 26)
						(Print 216 27 #at -1 144)
					)
					((Said '/building')
						(if playingAsPatti
							(Print 216 28)
						else
							(Print 216 29)
						)
					)
					((Said '[/area]')
						(if playingAsPatti
							(Print 216 28)
						else
							(Print 216 30)
							(Print 216 31 #at -1 144)
						)
					)
				)
			)
		)
	)
)

(instance aKandBB of Prop
	(properties
		view 218
	)
	
	(method (init)
		(super init:)
		(self posn: 246 42 setScript: KandBBScript)
	)
)

(instance KandBBScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 3)
			)
			(1
				(aKandBB loop: 0 setCycle: Forward)
				(= cycles (Random 20 40))
			)
			(2
				(aKandBB setCel: 3)
				(= cycles (Random 20 40))
			)
			(3
				(aKandBB loop: 1 setCycle: Forward)
				(= cycles (Random 20 40))
			)
			(4
				(aKandBB setCel: 3)
				(= cycles (Random 20 40))
			)
			(5
				(if (== 1 (Random 1 4))
					(aKandBB loop: 2 setCycle: EndLoop)
					(= cycles (Random 80 140))
				else
					(self changeState: 1)
				)
			)
			(6
				(aKandBB setCycle: BegLoop)
				(self changeState: 1)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'address/babe,babe,exwife')
			(switch (++ talkCount)
				(1
					(Print 216 45)
					(Print 216 46)
				)
				(2
					(Print 216 47)
					(Print 216 46)
				)
				(3
					(Print 216 48)
					(Print 216 49)
					(Print 216 50)
				)
				(else 
					(Print 216 51)
					(Print 216 52)
					(Print 216 53)
				)
			)
		)
		(if (Said '/club,club')
			(Print 216 54)
		)
		(if (Said 'look/babe,babe,exwife')
			(if (> filthLevel 3)
				(Print 216 55)
			else
				(Print 216 56)
			)
		)
	)
)

(instance blockFence2 of Block
	(properties
		top 190
		bottom 333
		right 190
	)
)

(instance blockFence1 of Block
	(properties
		top 200
		bottom 333
		right 333
	)
)

(instance aCredit1 of Prop
	(properties
		y 131
		x 288
		view 219
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 15 ignoreActors:)
	)
)

(instance aCredit2 of Prop
	(properties
		y 154
		x 288
		view 219
		loop 1
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 15 ignoreActors: setScript: CreditsScript)
	)
)

(instance CreditsScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 4)
			)
			(1
				(aCredit1 setCycle: EndLoop)
				(= cycles 16)
			)
			(2
				(aCredit2 setCycle: EndLoop)
				(= cycles 22)
			)
			(3
				(aCredit2 setCycle: BegLoop self)
			)
			(4
				(aCredit2 setLoop: 2 setCycle: EndLoop)
				(= cycles 22)
			)
			(5
				(Bset fCredits216)
				(aCredit1 setCycle: BegLoop)
				(aCredit2 setCycle: BegLoop self)
			)
			(6
				(aCredit1 dispose:)
				(aCredit2 dispose:)
			)
		)
	)
)

(instance aMailBox of Prop
	(properties
		y 107
		x 134
		view 216
		signal stopUpdOn
	)
)
