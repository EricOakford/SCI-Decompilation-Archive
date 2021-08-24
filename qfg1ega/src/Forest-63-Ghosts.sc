;;; Sierra Script 1.0 - (do not remove this comment)
(script# 63)
(include game.sh)
(use Main)
(use Motion)
(use Game)

(public
	rm63 0
)

(instance rm63 of Room
	(properties
		picture 701
		style DISSOLVE
		horizon 90
		north 51
		east 64
		south 71
		west 62
	)
	
	(method (init)
		(if Night
			(Load SCRIPT GHOSTS)
		)
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
			(51
				(ego posn: 180 92 setMotion: MoveTo 180 190)
			)
			(62
				(ego posn: 1 140 setMotion: MoveTo 320 140)
			)
			(64
				(ego posn: 318 140 setMotion: MoveTo 0 140)
			)
			(71
				(ego posn: 160 188 setMotion: MoveTo 160 0)
			)
		)
		(self setRegions: CEMETERY)
	)
	
	(method (dispose)
		(Bset fBeenIn63)
		(super dispose:)
	)
)
