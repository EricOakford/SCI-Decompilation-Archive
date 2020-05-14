;;; Sierra Script 1.0 - (do not remove this comment)
(script# 48)
(include game.sh)
(use Main)
(use BassSetter)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use User)
(use System)

(public
	rm48 0
)

(local
	local0
	egoX
	egoY
	oldScore
	newBassSetter
)
(procedure (NearFallPoint)
	(theSound stop:)
	(cls)
	(= score (+ score 1))
	(StatusLine doit:)
	(Print 48 0 #at -1 20 #time 5 #draw)
)

(instance theSound of Sound
	(properties
		number 13
	)
)

(instance rm48 of Room
	(properties
		picture 48
		horizon 1
	)
	
	(method (init)
		(Load VIEW 156)
		(Load VIEW 157)
		(Load VIEW 158)
		(Load VIEW 159)
		(Load VIEW 151)
		(Load SOUND 13)
		(Load SOUND 14)
		(Load SOUND 15)
		(super init:)
		(theSound init:)
		(self setScript: rm48Script)
		(= oldScore score)
		(= currentEgoView 151)
		(NormalEgo)
		(ego
			posn: 312 83
			setPri: 11
			setStep: 3 1
			loop: 1
			init:
			baseSetter: (= newBassSetter (BassSetter new:))
		)
		(newBassSetter radii: 6)
		(HandsOff)
	)
)

(instance rm48Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(cond 
			(
			(and (& (ego onControl:) $1000) (== state 11)) (self changeState: 12))
			((and (& (ego onControl:) $0800) (== state 8)) (self changeState: 9))
			((and (& (ego onControl:) $0400) (== state 5)) (self changeState: 6))
			((and (& (ego onControl:) $0200) (== state 1)) (self changeState: 2))
			(
			(and (& (ego onControl:) $0010) (== state 11)) (self changeState: 25))
			((and (& (ego onControl:) $0008) (== state 8)) (self changeState: 21))
			((and (& (ego onControl:) $0004) (== state 5)) (self changeState: 18))
			((and (& (ego onControl:) $0002) (== state 1)) (self changeState: 15))
			(
				(or
					(== state 1)
					(== state 5)
					(== state 8)
					(== state 11)
				)
				(= egoX (ego x?))
				(= egoY (ego y?))
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 3))
			(1
				(User canControl: TRUE canInput: TRUE)
				(Print 48 9)
			)
			(2
				(HandsOff)
				(theSound dispose:)
				(ego
					illegalBits: 0
					setPri: 10
					setMotion: MoveTo 220 95 self
				)
			)
			(3
				(ego setPri: 9 setMotion: MoveTo 270 95 self)
			)
			(4
				(= currentEgoView 157)
				(ego
					view: currentEgoView
					setPri: 9
					setStep: 2 1
					setMotion: MoveTo 203 106 self
				)
				(newBassSetter radii: 5)
			)
			(5
				(User canControl: 1 canInput: 1)
				(ego illegalBits: -32768)
			)
			(6
				(HandsOff)
				(theSound dispose:)
				(ego
					illegalBits: 0
					setPri: 8
					setMotion: MoveTo (+ (ego x?) 44) (ego y?) self
				)
			)
			(7
				(= currentEgoView 158)
				(ego
					view: currentEgoView
					setPri: 7
					setStep: 1 1
					posn: (ego x?) 99
					setMotion: MoveTo 132 99 self
				)
				(newBassSetter radii: 4)
			)
			(8
				(User canControl: TRUE canInput: TRUE)
				(ego illegalBits: -32768)
			)
			(9
				(HandsOff)
				(theSound dispose:)
				(ego
					illegalBits: 0
					setPri: 6
					setMotion: MoveTo (+ (ego x?) 12) (ego y?) self
				)
			)
			(10
				(= currentEgoView 159)
				(ego
					view: currentEgoView
					setPri: 5
					posn: 99 123
					setMotion: MoveTo 65 123 self
				)
				(newBassSetter radii: 3)
			)
			(11
				(User canControl: TRUE canInput: TRUE)
				(ego illegalBits: -32768)
			)
			(12
				(HandsOff)
				(ego
					illegalBits: 0
					setPri: 4
					setMotion: MoveTo (+ (ego x?) 22) (ego y?) self
				)
			)
			(13
				(User canControl: 0 canInput: 1)
				(= currentEgoView 151)
				(ego hide: view: currentEgoView baseSetter: 0)
				(if newBassSetter (newBassSetter dispose:))
				(Print 48 10)
				(Print 48 11)
				(= cycles 50)
			)
			(14
				(= cycles 0)
				(Print 48 12)
				(if (!= score oldScore)
					(= score oldScore)
					(StatusLine doit:)
					(Print 48 13 #at -1 152)
				)
				(curRoom newRoom: 50)
			)
			(15
				(HandsOff)
				(ego
					view: 156
					illegalBits: 0
					setLoop: 0
					cel: 0
					setCycle: EndLoop self
				)
				(Print 48 14 #at -1 20 #dispose #draw)
			)
			(16 (ego setCycle: BegLoop self))
			(17
				(NormalEgo 1)
				(ego posn: egoX egoY setPri: 11 setStep: 3 1)
				(= state 1)
				(NearFallPoint)
			)
			(18
				(HandsOff)
				(theSound number: 13 play:)
				(ego illegalBits: 0 setLoop: 4 cel: 0 setCycle: EndLoop self)
				(Print 48 14 #at -1 20 #dispose #draw)
			)
			(19
				(ego setLoop: 5 cel: 0 setCycle: EndLoop self)
			)
			(20
				(NormalEgo 1)
				(ego posn: egoX egoY setPri: 9 setStep: 2 1)
				(= state 5)
				(NearFallPoint)
			)
			(21
				(HandsOff)
				(ego illegalBits: 0 setLoop: 4 cel: 0 setCycle: EndLoop self)
				(theSound number: 14 play:)
				(Print 48 14 #at -1 20 #dispose #draw)
			)
			(22
				(ego setLoop: 5 cel: 0 setCycle: Forward)
				(= cycles 22)
			)
			(23
				(ego setLoop: 6 cel: 0 setCycle: EndLoop self)
			)
			(24
				(NormalEgo 1)
				(ego posn: egoX egoY setPri: 7 setStep: 1 1)
				(= state 8)
				(NearFallPoint)
			)
			(25
				(HandsOff)
				(ego illegalBits: 0 setLoop: 4 cel: 0 setCycle: EndLoop self)
				(theSound number: 15 play:)
				(= local0 0)
				(Print 48 14 #at -1 20 #dispose #draw)
			)
			(26
				(ego setLoop: 5 cel: 0 setCycle: EndLoop self)
				(if (> 3 (++ local0)) (-- state))
			)
			(27
				(ego setLoop: 4 setCel: 255 setCycle: BegLoop self)
			)
			(28
				(if (ego inRect: 34 118 39 123)
					(= egoX 38)
					(= egoY 120)
				)
				(NormalEgo 1)
				(ego posn: egoX egoY setPri: 5 setStep: 1 1)
				(= state 11)
				(NearFallPoint)
			)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(if (Said '/i') (Print 48 1))
			(if (Said 'dive,hop')
				(Print 48 2)
				(Print 48 3 #at -1 152)
			)
			(if (Said '[/airport,cliff]') (Print 48 4))
		)
		(if
			(or
				(Said 'alter,wear,(conceal<on)/bra,bra')
				(Said 'alter,drain,(get<off)/(bra<bathing),bikini,job')
				(Said 'get<dress')
			)
			(if (!= state 13)
				(Print 48 5)
			else
				(theGame changeScore: 6)
				(= oldScore (+ oldScore 6))
				(if (== stuffedBra 6)
					(Print 48 6)
				else
					((inventory at: 18) moveTo: -1)
					(Print 48 7)
				)
				(Print 48 8 #at -1 152)
				(= currentEgoView 149)
				(= stuffedBra FALSE)
				(ego view: currentEgoView)
				((inventory at: iBikiniTop) moveTo: -1)
				((inventory at: iBikiniBottom) moveTo: -1)
				(self changeState: 14)
			)
		)
	)
)
