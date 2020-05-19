;;; Sierra Script 1.0 - (do not remove this comment)
(script# 80)
(include game.sh)
(use Main)
(use Intrface)
(use LoadMany)
(use DPath)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm80 0
)

(instance rm80 of Room
	(properties
		picture 80
	)
	
	(method (init)
		(LoadMany VIEW 160 33 117)
		(Load PICTURE 81)
		(self style: WIPERIGHT)
		(super init:)
		(if (Btst fGoatFollows)
			(Print 80 0)
			(Bclr fGoatFollows)
		)
		(switch theCarrier
			(carrierCONDOR
				(carrier view: 160)
				(ego view: 33 loop: 0)
			)
			(carrierWITCH
				(carrier view: 117)
				(ego view: 117 loop: 2)
				((ScriptID 0 23) number: 57 loop: -1 play:)
			)
		)
		(HandsOff)
		(ego
			posn: -42 142
			setCycle: Forward
			cycleSpeed: 2
			setMotion: 0
			setPri: 15
			ignoreHorizon: TRUE
			ignoreActors: TRUE
			illegalBits: 0
			setPri: 7
			init:
		)
		(carrier
			setCycle: Forward
			cycleSpeed: 1
			setPri: 6
			init:
			setScript: fly
		)
	)
	
	(method (doit)
		(super doit: &rest)
		(switch theCarrier
			(carrierCONDOR
				(ego posn: (carrier x?) (+ (carrier y?) 61))
			)
			(carrierWITCH
				(ego posn: (+ (carrier x?) 9) (+ (carrier y?) 26))
			)
		)
	)
)

(instance carrier of Actor
	(properties
		x -42
		y 142
		description {condor}
	)
)

(instance fly of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(carrier setMotion: DPath 160 100 330 80 self)
			)
			(1
				(curRoom picture: 81 style: WIPERIGHT drawPic: 81)
				(self cue:)
			)
			(2
				(carrier
					posn: -42 100
					setMotion: DPath 160 100 350 150 self
				)
			)
			(3
				(HandsOn)
				((ScriptID 0 23) loop: 1 fade:)
				(curRoom newRoom:
					(switch theCarrier
						(carrierCONDOR 48)
						(carrierWITCH 90)
					)
				)
			)
		)
	)
)
