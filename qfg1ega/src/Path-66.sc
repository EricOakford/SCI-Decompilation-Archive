;;; Sierra Script 1.0 - (do not remove this comment)
(script# 66)
(include game.sh)
(use Main)
(use Motion)
(use Game)

(public
	rm66 0
)

(instance rm66 of Room
	(properties
		picture 66
		style DISSOLVE
		horizon 90
		north 56
		east 67
		south 75
		west 65
	)
	
	(method (init)
		(super init:)
		(StatusLine enable:)
		(self setLocales: FOREST)
		(NormalEgo)
		(ego init:)
		(switch prevRoomNum
			(56
				(ego posn: 190 92 setMotion: MoveTo 190 190)
			)
			(67
				(ego posn: 318 140 setMotion: MoveTo 0 140)
			)
			(65
				(ego posn: 1 140 setMotion: MoveTo 320 140)
			)
			(75
				(ego posn: 200 188 setMotion: MoveTo 200 0)
			)
		)
	)
	
	(method (dispose)
		(Bset fBeenIn66)
		(super dispose:)
	)
)
