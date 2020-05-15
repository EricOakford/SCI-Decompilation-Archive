;;; Sierra Script 1.0 - (do not remove this comment)
(script# 92)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm92 0
)

(local
	local0
	aWaterfall
	aFog
	aDoor
	aCopter
)
(instance theSound of Sound
	(properties
		priority 30
		loop -1
	)
)

(instance rm92 of Room
	(properties
		picture 92
		style IRISOUT
		horizon 5
	)
	
	(method (init)
		(Load VIEW 806)
		(Load VIEW 821)
		(super init:)
		((= aCopter (Actor new:))
			view: 821
			ignoreActors:
			illegalBits: 0
			setPri: 6
			setCycle: Forward
			setStep: 2 1
			setScript: islandScript
		)
		(self setScript: rm92Script)
		(cond 
			((== endGameState 100)
				(Load SOUND 17)
				(theSound number: 17 play:)
				(aCopter posn: 103 122)
				(rm92Script changeState: 16)
			)
			((== endGameState 102)
				(Load SOUND 17)
				(theSound number: 17 play:)
				(aCopter posn: 223 92)
				(rm92Script changeState: 23)
			)
			(else
				(Load SOUND 102)
				(theSound number: 102 play:)
				(aCopter posn: -7 92)
				(rm92Script changeState: 1)
			)
		)
		(if (!= endGameState 102)
			((= aWaterfall (Prop new:))
				view: 806
				ignoreActors:
				loop: 0
				posn: 203 87
				setPri: 10
				setCycle: Forward
				cycleSpeed: 1
				init:
			)
			((= aFog (Prop new:))
				view: 806
				ignoreActors:
				loop: 1
				cel: 0
				posn: 206 108
				cycleSpeed: (if (== endGameState 100) 1 else 5)
				setPri: 14
				init:
				hide:
			)
			((= aDoor (Prop new:))
				view: 806
				ignoreActors:
				loop: 3
				cel: 0
				posn: 205 93
				cycleSpeed: 5
				setPri: 4
				init:
			)
		)
		(aCopter init:)
		(HandsOff)
		(= currentStatus egoWONGAME)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(event claimed: TRUE)
		(return
			(if
				(and
					(== (event type?) keyDown)
					(== (event message?) `#2)
				)
				(event claimed: TRUE)
				(DoSound SoundOn (not (DoSound SoundOn)))
			else
				0
			)
		)
	)
)

(instance rm92Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1 (= cycles 30))
			(2
				(Print 92 0 #at -1 15 #width 280 #time 12)
				(islandScript changeState: 1)
			)
			(3
				(Print 92 1 #at -1 15 #width 280 #time 12 #draw)
				(= cycles 20)
			)
			(4
				(aCopter setMotion: MoveTo 186 93 self)
				(= cycles 40)
			)
			(5
				(Print 92 2 #at -1 15 #width 280 #time 14 #draw)
				(Print 92 3 #font bigFont #time 5)
				(= cycles 40)
			)
			(6
				(islandScript changeState: 3)
			)
			(8
				(islandScript changeState: 5)
			)
			(9
				(aCopter setMotion: MoveTo 223 93 self)
			)
			(10
				(islandScript changeState: 7)
			)
			(11
				(islandScript changeState: 9)
			)
			(12
				(islandScript changeState: 11)
			)
			(13 (= cycles 20))
			(14
				(Print 92 4 #at -1 15 #width 280 #time 8 #draw)
				(= seconds 3)
			)
			(15 (curRoom newRoom: 93))
			(16 (= cycles 30))
			(17
				(aFog show: setScript: fogScript)
				(aCopter setMotion: MoveTo 98 93 self)
			)
			(18
				(islandScript changeState: 3)
			)
			(19
				(aCopter setMotion: MoveTo 186 93 self)
			)
			(20
				(islandScript changeState: 5)
			)
			(21
				(aCopter setMotion: MoveTo 223 93 self)
			)
			(22 (curRoom newRoom: 85))
			(23
				(aCopter setMotion: MoveTo 149 93)
				(= cycles 20)
			)
			(24
				(Print 92 5)
				(Print (Format @str 92 6 introductoryPhrase))
				(= cycles 20)
			)
			(25 (Print 92 7) (= cycles 10))
			(26 (Print 92 8) (= cycles 20))
			(27
				(aCopter setMotion: MoveTo 246 120 self)
			)
			(28
				(= endGameState 103)
				(curRoom newRoom: 75)
			)
		)
	)
)

(instance islandScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(aFog show: setCycle: EndLoop self)
			)
			(2
				(aFog stopUpd:)
				(rm92Script cue:)
			)
			(3
				(aWaterfall cycleSpeed: 2 setLoop: 2 setCycle: EndLoop self)
			)
			(4
				(aWaterfall hide:)
				(rm92Script cue:)
			)
			(5
				(aDoor setCycle: EndLoop self)
			)
			(6
				(rm92Script cue:)
			)
			(7
				(aDoor setCycle: BegLoop self)
			)
			(8 (rm92Script cue:))
			(9
				(aWaterfall show: setLoop: 4 cel: 0 setCycle: EndLoop self)
			)
			(10
				(aWaterfall setLoop: 0 cycleSpeed: 0 setCycle: Forward)
				(rm92Script cue:)
			)
			(11
				(aFog show: setCycle: BegLoop self)
			)
			(12
				(aFog hide:)
				(rm92Script cue:)
			)
		)
	)
)

(instance fogScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(= cycles 5)
			)
			(1
				(aFog setCycle: EndLoop self)
			)
			(2
				(aFog setCycle: BegLoop self)
			)
			(3
				(self changeState: 0)
			)
		)
	)
)
