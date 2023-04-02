;;; Sierra Script 1.0 - (do not remove this comment)
(script# 172)
(include game.sh)
(use Main)
(use Procs)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rmBrig 0
)

(instance rmBrig of Room
	(properties
		picture pLeaderToElsa
		style IRISOUT
	)
	
	(method (init)
		(Load VIEW vBrigandLeaderFight)
		(Load SOUND 70)
		(super init:)
		(leader
			init:
			setScript: leaderToElsa
		)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(super dispose:)
	)
)

(instance leader of Prop
	(properties
		x 139
		y 135
		view vBrigandLeaderFight
		cycleSpeed 9
	)
	
	(method (init)
		(= nightPalette (+ vBrigandLeaderFight 1000))
		(PalVary PALVARYTARGET (+ vBrigandLeaderFight 1000))
		(AssertPalette vBrigandLeaderFight)
		(super init:)
	)
)

(instance leaderToElsa of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= ticks 60)
				(theGame setCursor: theCursor FALSE)
			)
			(1
				(SolvePuzzle f172DispelLeader 35)
				(elsaChange init: play:)
				(leader setCycle: EndLoop self)
			)
			(2
				(= ticks 90)
			)
			(3
				(curRoom newRoom: 97)
			)
		)
	)
)

(instance elsaChange of Sound
	(properties
		number 70
		priority 3
	)
)
