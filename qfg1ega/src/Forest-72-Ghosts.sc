;;; Sierra Script 1.0 - (do not remove this comment)
(script# 72)
(include game.sh)
(use Main)
(use Motion)
(use Game)

(public
	rm72 0
)

(instance rm72 of Room
	(properties
		picture 700
		style DISSOLVE
		horizon 90
		north 64
		east 73
		south 79
		west 71
	)
	
	(method (init)
		(if Night (Load SCRIPT GHOSTS))
		(super init: &rest)
		(StatusLine enable:)
		(self setLocales: FOREST)
		(NormalEgo)
		(ego init:)
		(if Night
			(switch (Random 1 5)
				(1
					((ScriptID GHOSTS 5) init: setScript: (ScriptID GHOSTS 0))
				)
				(2
					((ScriptID GHOSTS 6) init: setScript: (ScriptID GHOSTS 1))
				)
				(3
					((ScriptID GHOSTS 7) init: setScript: (ScriptID GHOSTS 4))
				)
				(4
					((ScriptID GHOSTS 5) init: setScript: (ScriptID GHOSTS 0))
				)
				(5
					((ScriptID GHOSTS 7) init: setScript: (ScriptID GHOSTS 3))
				)
			)
		)
		(switch prevRoomNum
			(64
				(ego posn: 140 92 setMotion: MoveTo 140 190)
			)
			(71
				(ego posn: 1 140 setMotion: MoveTo 320 140)
			)
			(79
				(ego posn: 160 188 setMotion: MoveTo 160 0)
			)
			(73
				(ego posn: 318 140 setMotion: MoveTo 0 140)
			)
		)
		(self setRegions: CEMETERY)
	)
	
	(method (dispose)
		(Bset fBeenIn72)
		(super dispose:)
	)
)
