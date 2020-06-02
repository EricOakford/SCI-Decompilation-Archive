;;; Sierra Script 1.0 - (do not remove this comment)
(script# SLEEPALLNIGHT)
(include game.sh)
(use Main)
(use Sleep)
(use Procs)
(use Motion)
(use System)

(public
	SleepAllNight 0
)

(instance SleepAllNight of Script
	(properties)
	
	(method (dispose)
		(NormalEgo)
		(super dispose:)
		(DisposeScript SLEEPALLNIGHT)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (ego setHeading: 180 self))
			(1
				(theGame setCursor: waitCursor TRUE)
				(ego
					view: 551
					setLoop: 0
					setCel: 0
					posn: (- (ego x?) 3) (+ (ego y?) 2)
					setCycle: EndLoop self
				)
			)
			(2 (= ticks 12))
			(3
				(ego
					setLoop: 1
					setCel: 0
					posn: (- (ego x?) 4) (+ (ego y?) 1)
					setCycle: EndLoop self
				)
			)
			(4
				(PalVary PALVARYSTART (curRoom picture?) 2)
				(if nightPalette (PalVary PALVARYTARGET nightPalette))
				(= seconds 5)
			)
			(5 (= seconds 2))
			(6
				(PalVary PALVARYREVERSE 4)
				(= seconds 2)
			)
			(7 (= seconds 2))
			(8
				(EgoSleeps 5 0)
				(ego setCycle: BegLoop self)
			)
			(9
				(ego
					setLoop: 0
					setCel: 6
					posn: (+ (ego x?) 4) (- (ego y?) 1)
					setCycle: BegLoop self
				)
			)
			(10
				(ego posn: (+ (ego x?) 3) (- (ego y?) 2))
				(HandsOn)
				(NormalEgo)
			)
		)
	)
)
