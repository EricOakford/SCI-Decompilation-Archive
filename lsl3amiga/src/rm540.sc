;;; Sierra Script 1.0 - (do not remove this comment)
(script# 540)
(include game.sh)
(use Main)
(use Intrface)
(use LoadMany)
(use Wander)
(use Jump)
(use Motion)
(use Game)
(use Invent)
(use Actor)
(use System)

(public
	rm540 0
)

(local
	pigAppears
	[msgBuf 33]
	[titleBuf 22]
)
(instance rm540 of Room
	(properties
		picture 540
		horizon 78
		north 550
		south 530
	)
	
	(method (init)
		(super init:)
		(self setScript: RoomScript)
		(if (== prevRoomNum 550)
			(ego posn: 233 80)
		else
			(ego posn: 233 188)
		)
		(if (not (Btst fBeatFeralPig))
			(Load VIEW 541)
			(Load VIEW 542)
			(Load VIEW 543)
			(Load SCRIPT JUMP)
			(Load SCRIPT WANDER)
			(Load SOUND 540)
			(Load SOUND 541)
			(Load SOUND 12)
			(Load SOUND 561)
			(Load SOUND 530)
			(aPig init:)
			(aBra init:)
		)
		(= currentEgoView 803)
		((Inventory at: iDress) view: 31)
		(= currentStatus egoNORMAL)
		(NormalEgo loopN)
		(ego init:)
	)
	
	(method (newRoom n)
		(super newRoom: n)
		(LoadMany FALSE WANDER)
	)
)

(instance RoomScript of Script
	(method (doit)
		(super doit:)
		(if
			(and
				(== state 7)
				(== 541 (ego view?))
				(== 2 (ego loop?))
				(== 1 (ego cel?))
			)
			(soundFX number: 530 loop: 1 play:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0)
			(1
				(HandsOff)
				(Bset fRemovedBra)
				(if (not (Btst fBraPoints))
					(Bset fBraPoints)
					(theGame changeScore: 5)
				)
				(Ok)
				(ego
					view: 541
					setLoop: 0
					cel: 0
					illegalBits: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(2
				(Print 540 25 #at -1 10)
				(NormalEgo loopN)
			)
			(3
				(HandsOff)
				(Ok)
				(Bclr fRemovedBra)
				(Bclr fCoconutsInBra)
				(ego
					view: 541
					setLoop: 0
					setCel: 255
					illegalBits: 0
					cycleSpeed: 1
					setCycle: BegLoop self
				)
			)
			(4
				(Print 540 26)
				(NormalEgo loopN)
			)
			(5
				(HandsOff)
				(Bset fCoconutsInBra)
				(if (not (Btst fCoconutPoints))
					(Bset fCoconutPoints)
					(theGame changeScore: 45)
				)
				(Ok)
				(ego
					view: 541
					setLoop: 1
					cel: 0
					illegalBits: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(6
				(Print 540 27)
				(NormalEgo loopN)
				(Print 540 28 #at -1 144)
			)
			(7
				(HandsOff)
				(Ok)
				(Bclr fRemovedBra)
				(music stop:)
				(= saveSpeed (theGame setSpeed: 6))
				(= currentStatus 540)
				(ego
					view: 541
					setLoop: 2
					illegalBits: 0
					cel: 0
					setCycle: Forward
				)
				(= cycles (* 5 (- (NumCels ego) 1)))
			)
			(8
				(NormalEgo loopN)
				(HandsOff)
				(aBra
					ignoreHorizon:
					posn: (+ (ego x?) 23) (- (ego y?) 43)
					setLoop: 3
					setCycle: Forward
					setStep: 8 8
					setMotion: JumpTo (- (aPig x?) 2) (- (aPig y?) 13) self
				)
				(PigScript changeState: 9)
			)
			(9
				(PigScript cue:)
				(aBra dispose:)
				(if (Btst fCoconutsInBra)
					(ego
						put: iBra -1
						put: iCoconuts -1
					)
					(self changeState: 11)
				else
					(= seconds 3)
				)
			)
			(10
				(Print 540 29)
				(theGame setScript: (ScriptID DYING))
				((ScriptID DYING)
					caller: 543
					register: (Format @msgBuf 540 30)
					next: (Format @titleBuf 540 31)
				)
			)
			(11
				(= cycles 0)
				(= seconds 3)
			)
			(12
				(Print 540 32 #at -1 10)
				(NormalEgo 3)
				(= currentStatus egoNORMAL)
				(Print 540 33 #at -1 144)
				(music number: 599 loop: musicLoop play:)
				(Bset fBeatFeralPig)
				(theGame changeScore: 100)
				(theGame setSpeed: saveSpeed)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			((Said 'use/bra,coconut')
				(Print 540 0)
			)
			((Said 'use/marker')
				(Print 540 1)
			)
			((and (ego has: iPenthouseKey) (Said '/key'))
				(Print 540 2)
			)
			((Said 'climb/palm')
				(Print 540 3)
			)
			((Said '(backdrop<on),wear/bra')
				(cond 
					((InRoom iBra 484)
						(Print 540 4)
					)
					((InRoom iBra -1)
						(DontHave)
					)
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					((not (Btst fRemovedBra))
						(Print 540 5)
					)
					((Btst fCoconutsInBra)
						(Print 540 6)
					)
					(else
						(self changeState: 3)
					)
				)
			)
			((Said 'drain,(off<get),(get<off)/bra')
				(cond 
					((InRoom iBra 484)
						(Print 540 4)
					)
					((InRoom iBra -1)
						(DontHave)
					)
					((Btst fRemovedBra)
						(Print 540 7)
					)
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					(else
						(self changeState: 1)
					)
				)
			)
			(
				(and
					(Btst fCoconutsInBra)
					(Said 'drain,(out<get),(get<out)/coconut')
				)
				(Bclr fCoconutsInBra)
				(Print 540 8)
			)
			((Said 'backdrop/coconut/bra')
				(cond 
					((not (ego has: iBra))
						(Print 540 9)
					)
					((not (ego has: iCoconuts))
						(Print 540 10)
						(if (>= filthLevel 3)
							(Print 540 11
								#at -1 144
							)
						)
					)
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					((not (Btst fRemovedBra))
						(Print 540 12)
						(Print 540 13
							#at -1 144
						)
					)
					((Btst fCoconutsInBra)
						(Print 540 14)
					)
					(else
						(self changeState: 5)
					)
				)
			)
			(
				(or
					(Said 'use/bra/throw')
					(Said 'throw/coconut/animal')
					(Said 'throw/bra')
				)
				(cond 
					((InRoom iBra 484)
						(Print 540 4)
					)
					((not (ego has: iBra))
						(DontHave)
					)
					((not (Btst fRemovedBra))
						(Print 540 15)
					)
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					((!= (PigScript state?) 2)
						(Print 540 16)
					)
					(else
						(self changeState: 7)
					)
				)
			)
			((Said 'throw/coconut')
				(if (ego has: iCoconuts)
					(Print 540 17)
				else
					(Print 540 10)
					(if (> filthLevel 1)
						(Print 540 18 #at -1 144)
					)
				)
			)
			((Said 'throw>')
				(cond 
					((Said '[/noword]')
						(Print 540 19)
					)
					((Said '/anyword[/noword]')
						(Print 540 20)
					)
				)
			)
			((Said 'climb,crawl')
				(Print 540 21)
			)
			((Said 'look>')
				(cond 
					((Said '/creek')
						(Print 540 22)
					)
					((Said '[/area]')
						(Print 540 23)
						(Print 540 24
							#at -1 144
						)
					)
				)
			)
		)
	)
)

(instance aBra of Actor
	(properties
		y 999
		x 999
		view 541
		illegalBits $0000
	)
)

(instance aPig of Actor
	(properties
		y 111
		x 288
		view 540
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self
			ignoreHorizon:
			setLoop: 0
			setPri: 1
			setCycle: Forward
			setStep: 4 4
			setScript: PigScript
		)
	)
)

(instance PigScript of Script
	(method (doit)
		(super doit:)
		(cond 
			((== currentStatus 540))
			((and (< state 4) (& (ego onControl:) cBLUE))
				(self changeState: 4)
			)
			(
				(and
					(& (ego onControl: origin) cRED)
					(or (== state 1) (== state 2))
				)
				(self changeState: 3)
			)
			(
				(and
					(& (ego onControl: origin) cGREEN)
					(or (== state 0) (== state 3))
				)
				(self changeState: 1)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0)
			(1
				(music number: 540 loop: -1 play:)
				(aPig setMotion: MoveTo 216 97 self)
			)
			(2
				(if (not pigAppears)
					(= pigAppears TRUE)
					(Print 540 34 #at 10 5 #width 290)
				)
				(aPig
					illegalBits: -3
					setMotion: Wander
					setPri: -1
					setStep: 2 2
				)
			)
			(3
				(music fade:)
				(aPig
					illegalBits: 0
					setPri: 1
					setMotion: MoveTo 288 111 self
				)
				(= state -1)
			)
			(4
				(= currentStatus egoDYING)
				(HandsOff)
				(Print 540 35 #at -1 10)
				(soundFX stop:)
				(music number: 541 loop: 1 play:)
				(aPig
					illegalBits: 0
					ignoreActors:
					setPri: -1
					setLoop: 1
					setCel: 0
					setCycle: EndLoop
					setStep: 4 10
					setMotion: MoveTo
						(- (aPig x?) (/ (- (aPig x?) (ego x?)) 2))
						-10
						self
				)
			)
			(5
				(aPig
					setLoop: 2
					setCycle: Forward
					setMotion: MoveTo (ego x?) (- (ego y?) 40) self
				)
			)
			(6
				(music number: 12 loop: 1 play:)
				(ego view: 542 cel: 0 setCycle: EndLoop)
				(aPig setMotion: MoveTo (ego x?) (ego y?) self)
			)
			(7
				(ego stopUpd:)
				(aPig setLoop: 0 setCycle: Forward)
				(= seconds 3)
			)
			(8
				(if (>= filthLevel 3)
					(Print 540 36 #at -1 10)
					(Print 540 37 #at -1 144)
				else
					(Print 540 38 #at -1 10)
					(Print 540 39 #at -1 10)
				)
				(theGame setScript: (ScriptID DYING))
				((ScriptID DYING)
					caller: 543
					register: (Format @msgBuf 540 40)
					next: (Format @titleBuf 540 41)
				)
			)
			(9
				(= seconds (= cycles 0))
				(aPig setMotion: 0)
			)
			(10
				(= seconds (= cycles 0))
				(music stop:)
				(if (Btst fCoconutsInBra)
					(soundFX number: 12 loop: 1 play:)
					(aPig
						illegalBits: 0
						setLoop: 3
						cel: 0
						setStep: 4 4
						setCycle: EndLoop
						setMotion: JumpTo 225 69 self
					)
				else
					(self changeState: 1)
				)
			)
			(11
				(soundFX number: 561 loop: 1 play:)
				(aPig setLoop: 4 cel: 0 setCycle: EndLoop self)
			)
			(12
				(RoomScript changeState: 11)
				(aPig setScript: 0 dispose:)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((Said '//animal>')
				(cond 
					((> (aPig x?) 275)
						(Print 540 42)
						(event claimed: TRUE)
					)
					((Said 'throw/anyword>')
						(if (Said '/bra')
							(event claimed: FALSE)
						else
							(Print 540 43)
							(event claimed: TRUE)
						)
					)
					(else
						(Print 540 44)
						(event claimed: TRUE)
					)
				)
			)
			((Said '/animal>')
				(cond 
					((> (aPig x?) 280)
						(Print 540 42)
						(event claimed: TRUE)
					)
					((Said 'feed/')
						(Print 540 45)
					)
					((Said 'attack,carve/')
						(Print 540 46)
					)
					((Said 'address')
						(Print 540 47)
					)
					((Said 'look/')
						(Print 540 48)
					)
					(else
						(Print 540 49)
						(event claimed: TRUE)
					)
				)
			)
		)
	)
)
