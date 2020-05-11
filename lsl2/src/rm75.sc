;;; Sierra Script 1.0 - (do not remove this comment)
(script# 75)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm75 0
)

(local
	triedToEnterWater
	kalalau
	cupid2
	cupid1
	helicopter
)
(instance theSound of Sound
	(properties
		loop -1
	)
)

(instance rm75 of Room
	(properties
		picture 75
		horizon 5
		south 74
		west 77
	)
	
	(method (init)
		(super init:)
		(NormalEgo)
		(self setScript: rm75Script)
		(if global111 (= endGameState 105))
		(cond 
			((== endGameState NULL)
				(= endGameState endMEETKALALAU)
				(= currentStatus egoMeetingTribe)
				(rm75Script changeState: 1)
				(Load VIEW 703)
				(Load VIEW 704)
				(Load VIEW 705)
				(Load VIEW 706)
				(Load FONT 7)
				(Load PICTURE 99)
				(Load SOUND 116)
				(theSound number: 116 init:)
				((= kalalau (Actor new:))
					view: 705
					loop: 0
					ignoreActors:
					illegalBits: 0
					posn: 190 124
					init:
				)
				((= cupid1 (Prop new:))
					view: 703
					ignoreActors:
					setLoop: 1
					posn: 217 127
					setCycle: Forward
					init:
					hide:
				)
				((= cupid2 (Prop new:))
					view: 703
					ignoreActors:
					setLoop: 0
					cel: 3
					posn: 84 127
					setCycle: Forward
					init:
					hide:
				)
				(HandsOff)
				(ego posn: 115 185)
			)
			((== endGameState 103)
				(= endGameState 104)
				(= currentStatus 22)
				(rm75Script changeState: 36)
				(Load VIEW 704)
				(Load VIEW 706)
				(Load VIEW 807)
				(Load SOUND 17)
				(theSound number: 17 play:)
				((= helicopter (Prop new:))
					view: 807
					posn: 319 136
					setCycle: Forward
					init:
				)
				((= kalalau (Actor new:))
					view: 704
					loop: 2
					ignoreActors:
					illegalBits: 0
					posn: -11 145
					setCycle: Walk
					init:
				)
				(HandsOff)
				(ego posn: 327 186)
			)
			(else
				(self setRegions: ISLAND)
				(if (== prevRoomNum 77)
					(ego posn: 2 135)
				else
					(ego posn: 115 185)
				)
			)
		)
		(ego init:)
	)
)

(instance rm75Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (& (ego onControl:) $0100)
			(if (== triedToEnterWater FALSE) (= triedToEnterWater TRUE) (Print 75 0))
		else
			(= triedToEnterWater FALSE)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1 (= seconds 3))
			(2
				(kalalau cycleSpeed: 1 setCycle: EndLoop self)
			)
			(3
				(kalalau setLoop: 1 setCycle: Forward)
				(= seconds 3)
			)
			(4
				(Print 75 10 #at -1 20)
				(Print 75 11 #font 7 #at -1 20 #width 222)
				(theSound play:)
				(= seconds 3)
			)
			(5
				(Print 75 12)
				(kalalau setLoop: 0 setCel: 255 setCycle: BegLoop self)
			)
			(6
				(kalalau
					cycleSpeed: 0
					setLoop: 2
					cel: 0
					setMotion: MoveTo 190 145
					setCycle: EndLoop self
				)
			)
			(7
				(kalalau
					view: 704
					setLoop: -1
					setCycle: Walk
					setMotion: MoveTo 190 164 self
				)
			)
			(8
				(kalalau loop: 1)
				(ego setMotion: MoveTo 115 164 self)
				(Print 75 13 #draw)
				(Print 75 14 #at -1 152)
			)
			(9
				(ego setLoop: 0)
				(= cycles 10)
			)
			(10
				(ego posn: 1115 163)
				(kalalau posn: 1190 163)
				(DrawPic 99 DISSOLVE)
				(= seconds 6)
			)
			(11
				(Print 75 15 #at -1 15 #width 280)
				(= seconds 5)
			)
			(12
				(DrawPic 75 DISSOLVE)
				(ego posn: 115 163)
				(kalalau posn: 190 163)
				(= seconds 3)
			)
			(13
				(ego view: 703 loop: 4 cel: 0 setCycle: EndLoop)
				(kalalau view: 703 loop: 6 cel: 0 setCycle: EndLoop self)
			)
			(14
				(ego loop: 5 setCycle: Forward)
				(kalalau loop: 7 setCycle: Forward)
				(= seconds 3)
			)
			(15
				(ego loop: 4 setCel: 255 setCycle: BegLoop)
				(kalalau loop: 6 setCel: 255 setCycle: BegLoop self)
			)
			(16
				(cupid2 show: setCycle: Forward)
				(cupid1 show: setCycle: Forward)
				(ego view: 100 loop: 0 setLoop: -1 setCycle: Walk)
				(kalalau view: 704 loop: 1 setLoop: -1 setCycle: Walk)
				(= seconds 4)
			)
			(17
				(cupid1 hide:)
				(cupid2 hide:)
				(= cycles 20)
			)
			(18
				(cupid2
					loop: 2
					cel: 0
					setCycle: EndLoop
					posn: 84 127
					show:
				)
				(cupid1
					loop: 2
					cel: 0
					setCycle: EndLoop self
					posn: 217 127
					show:
				)
			)
			(19
				(cupid2 loop: 3 cel: 0 setCycle: Forward)
				(cupid1 loop: 3 cel: 1 setCycle: Forward)
				(= seconds 3)
			)
			(20
				(cupid2 loop: 2 setCel: 255 setCycle: BegLoop)
				(cupid1 loop: 2 setCel: 255 setCycle: BegLoop self)
			)
			(21
				(cupid1 hide:)
				(cupid2 hide:)
				(= cycles 20)
			)
			(22
				(cupid2
					loop: 9
					cel: 0
					setCycle: Forward
					posn: 116 51
					show:
				)
				(= cycles 5)
			)
			(23
				(cupid1 loop: 8 cel: 0 setCycle: Forward posn: 236 51 show:)
				(= cycles 41)
			)
			(24
				(cupid1 hide:)
				(= cycles 5)
			)
			(25
				(cupid2 hide:)
				(= cycles 20)
			)
			(26
				(Print 75 16)
				(ego setMotion: MoveTo 142 164)
				(kalalau setMotion: MoveTo 158 164 self)
			)
			(27
				(kalalau hide:)
				(ego
					view: 706
					setLoop: 0
					cel: 0
					posn: 151 163
					setCycle: EndLoop self
				)
			)
			(28 (= seconds 5))
			(29 (ego setCycle: BegLoop self))
			(30
				(Print 75 17 #draw)
				(Print 75 18 #at -1 152)
				(kalalau show: ignoreActors: 0)
				(ego
					view: currentEgoView
					setLoop: -1
					loop: 0
					posn: 142 163
					setCycle: Walk
				)
				(= seconds 3)
			)
			(31
				(Print 75 19)
				(Print 75 20)
				(Print introductoryPhrase)
				(Print 75 21 #at 15 -1 #width 280)
				(Print 75 22)
				(Print 75 23 #at 15 -1 #width 280)
				(Print 75 24 #at 15 -1 #width 280)
				(Print 75 25)
				(Print 75 26 #at 15 -1 #width 280)
				(if (> filthLevel 4) (Print 75 27) else (Print 75 28))
				(Print 75 29 #at -1 152)
				(= seconds 3)
			)
			(32
				(theSound dispose:)
				(Print 75 30)
				(theGame changeScore: 10)
				(Print 75 31)
				(Print 75 32 #at -1 152)
				(= seconds 3)
			)
			(33
				(Print 75 33)
				(kalalau
					setMotion: MoveTo (kalalau x?) (- (kalalau y?) 5) self
				)
			)
			(34
				(kalalau setMotion: MoveTo -20 143)
				(= cycles 20)
			)
			(35
				(ego setMotion: MoveTo 0 143 self)
			)
			(36 (= seconds 3))
			(37
				(Print 75 34)
				(Print 75 35)
				(ego illegalBits: 0 setMotion: MoveTo 181 167 self)
			)
			(38
				(kalalau setMotion: MoveTo 76 156)
				(ego setMotion: MoveTo 92 158 self)
			)
			(39
				(Print 75 36)
				(Print 75 37)
				(kalalau hide:)
				(ego
					view: 706
					setLoop: 1
					cel: 0
					posn: 83 156
					setCycle: EndLoop self
				)
			)
			(40 (= seconds 5))
			(41 (ego setCycle: BegLoop self))
			(42
				(kalalau show: ignoreActors: 0)
				(ego
					view: currentEgoView
					setLoop: -1
					loop: 1
					posn: 92 157
					setCycle: Walk
				)
				(= seconds 3)
			)
			(43
				(Print 75 38)
				(ego setMotion: MoveTo 0 143 self)
				(kalalau setMotion: MoveTo -20 143)
			)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(if (Said '/fluid,lagoon') (Print 75 1) (Print 75 2))
			(if (Said '[/airport,/]')
				(Print 75 3)
				(Print 75 4)
				(Print 75 5 #at -1 152)
			)
		)
		(if (and (not (ego has: iSand)) (Said 'look/beach'))
			(Print 75 6)
		)
		(if (Said 'play,dig/beach') (Print 75 7))
		(if (Said 'get/beach')
			(cond 
				((!= currentStatus egoNormal) (PrintNotNow))
				((not ((inventory at: iSand) ownedBy: curRoomNum)) (PrintAlreadyTookIt))
				((not (& (ego onControl: origin) $4000)) (PrintNotCloseEnough))
				(else
					(ego get: iSand)
					(theGame changeScore: 3)
					(Print 75 8)
					(if (> filthLevel 10) (Print 75 9 #at -1 152))
				)
			)
		)
	)
)
