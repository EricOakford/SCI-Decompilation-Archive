;;; Sierra Script 1.0 - (do not remove this comment)
(script# 91)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm91 0
)

(local
	local0
	aMouth
	aCar
	aDog
)
(instance theSound of Sound
	(properties
		number 101
		priority 10
		loop -1
	)
)

(instance rm91 of Room
	(properties
		picture 23
		style IRISOUT
	)
	
	(method (init)
		(Load VIEW 192)
		(Load VIEW 253)
		(Load VIEW 802)
		(Load VIEW 803)
		(Load VIEW 804)
		(Load SOUND 101)
		(super init:)
		(theSound play:)
		((View new:)
			view: 253
			loop: 0
			cel: 0
			posn: 95 130
			setPri: 1
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 253
			loop: 0
			cel: 2
			posn: 136 139
			setPri: 9
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 253
			loop: 0
			cel: 2
			posn: 49 138
			setPri: 9
			addToPic:
		)
		((View new:)
			view: 253
			loop: 0
			cel: 2
			posn: 35 143
			setPri: 9
			addToPic:
		)
		((View new:)
			view: 253
			loop: 0
			cel: 3
			posn: 149 110
			setPri: 13
			addToPic:
		)
		((View new:)
			view: 253
			loop: 0
			cel: 1
			posn: 15 135
			setPri: 1
			addToPic:
		)
		((= aMouth (Prop new:))
			view: 802
			ignoreActors:
			setLoop: 1
			setCel: 0
			setPri: 15
			posn: 3 1152
			setCycle: Forward
			init:
		)
		((= aCar (Actor new:))
			view: 802
			ignoreActors:
			illegalBits: 0
			setLoop: 0
			setCel: 0
			setPri: 13
			posn: -64 222
			init:
		)
		((= aDog (Actor new:))
			view: 803
			ignoreActors:
			illegalBits: 0
			setLoop: 0
			setCel: 0
			setPri: 14
			posn: 31 150
			init:
			hide:
		)
		(ego
			view: 192
			ignoreActors:
			illegalBits: 0
			posn: 119 146
			setCel: 0
			setCycle: Walk
			setPri: -1
			setStep: 3 2
			init:
		)
		(self setScript: rm91Script)
	)
)

(instance rm91Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 185 146 self)
			)
			(1
				(ego loop: 1 posn: 179 147 setMotion: MoveTo 116 147 self)
			)
			(2
				(ego loop: 0 posn: 110 151 setMotion: MoveTo 167 151 self)
			)
			(3
				(ego setLoop: 1 posn: 155 155 setCel: 0 stopUpd:)
				(aCar setMotion: MoveTo 0 183 self)
			)
			(4
				(= seconds 3)
			)
			(5
				(Print 91 0
					#at -1 15
					#width 280
					#time 8
				)
				(aMouth posn: 3 152)
				(= seconds 3)
			)
			(6
				(Print 91 1
					#time 7
				)
				(aMouth posn: 3 1152)
				(= seconds 3)
			)
			(7
				(Print 91 2
					#at -1 15
					#width 280
					#time 6
				)
				(Print 91 3
					#at -1 15
					#width 280
					#time 12
				)
				(aMouth posn: 3 152)
				(= seconds 2)
			)
			(8
				(Print 91 4 #time 5)
				(aMouth posn: 3 1152)
				(= seconds 3)
			)
			(9
				(Print 91 5 #at -1 15 #width 280 #time 10)
				(Print 91 6 #time 9)
				(Print 91 7 #time 4)
				(aDog show:)
				(= seconds 3)
			)
			(10
				(aDog setCycle: EndLoop self)
			)
			(11
				(= cycles 5)
			)
			(12
				(aDog
					view: 804
					posn: 52 166
					setPri: -1
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo 193 161 self
				)
				(Print 91 8 #at -1 15 #width 280 #time 7)
				(aMouth posn: 3 152)
				(= cycles 22)
			)
			(13
				(Print 91 9 #time 8)
				(aMouth dispose:)
				(aCar setMotion: MoveTo -99 288)
			)
			(14
				(aDog setLoop: 2 setCycle: Forward)
				(= cycles 30)
			)
			(15
				(Print 91 10 #at -1 152 #time 3)
				(= cycles 30)
			)
			(16
				(aDog
					setLoop: 1
					setCycle: Walk
					setMotion: MoveTo 123 161 self
				)
			)
			(17
				(Print 91 11 #at -1 15 #width 280 #time 13)
				(Print 91 12 #at -1 15 #width 280 #time 4)
				(aDog setMotion: MoveTo -22 151)
				(= seconds 3)
			)
			(18
				(Print 91 13 #time 3)
				(curRoom newRoom: 92 IRISOUT)
			)
		)
	)
	
	(method (handleEvent event)
		(if (not (event claimed?)) (theGame restart:))
	)
)
