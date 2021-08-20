;;; Sierra Script 1.0 - (do not remove this comment)
(script# MEADOW)
(include game.sh)
(use Main)
(use Intrface)
(use LoadMany)
(use Extra)
(use Motion)
(use Game)
(use Actor)

(public
	meadow 0
)

(instance meadow of Room
	(properties
		picture rEranasPeace
		style IRISOUT
	)
	
	(method (init)
		(Load VIEW rEranasPeace)
		(LoadMany SOUND sEranasPeaceIBM sEranasPeace)
		(super init:)
		(ego
			view: 0
			posn: 160 188
			init:
			setMotion: MoveTo 160 150
		)
		(magicStone posn: 109 145 init:)
		(globalMusic
			number: (if (== numVoices 1) sEranasPeaceIBM else sEranasPeace)
			loop: 1
			play:
		)
		(fruit1 init: setPri: 5)
		(fruit2 init: setPri: 5)
		(fruit3 init: setPri: 5)
		(fruit4 init: setPri: 5)
		(fruit5 init: setPri: 5)
		(fruit6 init: setPri: 5)
		(fruit7 init: setPri: 5)
		(fruit8 init: setPri: 5)
		(fruit9 init: setPri: 5)
		(fruit10 init: setPri: 5)
		(fruit11 init: setPri: 5)
		(fruit12 init: setPri: 5)
		(fruit13 init: setPri: 5)
		(fruit14 init: setPri: 5)
		(= roomTimer 220)
		(Print 9 0
			#at -1 185
			#width 270
			#dispose
		)
	)
	
	(method (doit)
		(if (> roomTimer 1)
			(-- roomTimer)
		)
		(if (== roomTimer 1)
			(= roomTimer 0)
			(if modelessDialog
				(modelessDialog dispose:)
			)
			(curRoom newRoom: FOREST)
		)
		(super doit:)
	)
	
	(method (dispose)
		(DisposeScript EXTRA)
		(super dispose:)
	)
)

(instance fruit1 of Extra
	(properties
		y 43
		x 166
		view rEranasPeace
		loop 3
		cycleType ExtraEndLoop
		minPause 50
		maxPause 100
		minCycles 1
		maxCycles 1
	)
)

(instance fruit2 of Extra
	(properties
		y 60
		x 139
		view rEranasPeace
		loop 1
		cycleType ExtraEndLoop
		minPause 60
		maxPause 110
		minCycles 1
		maxCycles 1
	)
)

(instance fruit3 of Extra
	(properties
		y 49
		x 154
		view rEranasPeace
		loop 2
		cycleType ExtraEndLoop
		minPause 70
		maxPause 120
		minCycles 1
		maxCycles 1
	)
)

(instance fruit4 of Extra
	(properties
		y 50
		x 175
		view rEranasPeace
		loop 3
		cycleType ExtraEndLoop
		minPause 80
		maxPause 130
		minCycles 1
		maxCycles 1
	)
)

(instance fruit5 of Extra
	(properties
		y 43
		x 184
		view rEranasPeace
		loop 1
		cycleType ExtraEndLoop
		minPause 90
		maxPause 140
		minCycles 1
		maxCycles 1
	)
)

(instance fruit6 of Extra
	(properties
		y 60
		x 191
		view rEranasPeace
		loop 2
		cycleType ExtraEndLoop
		minPause 100
		maxPause 150
		minCycles 1
		maxCycles 1
	)
)

(instance fruit7 of Extra
	(properties
		y 66
		x 208
		view rEranasPeace
		loop 1
		cycleType ExtraEndLoop
		minPause 40
		maxPause 90
		minCycles 1
		maxCycles 1
	)
)

(instance fruit8 of Extra
	(properties
		y 48
		x 136
		view rEranasPeace
		loop 3
		cycleType ExtraEndLoop
		minPause 30
		maxPause 80
		minCycles 1
		maxCycles 1
	)
)

(instance fruit9 of Extra
	(properties
		y 71
		x 163
		view rEranasPeace
		loop 3
		cycleType ExtraEndLoop
		minPause 20
		maxPause 70
		minCycles 1
		maxCycles 1
	)
)

(instance fruit10 of Extra
	(properties
		y 49
		x 208
		view rEranasPeace
		loop 2
		cycleType ExtraEndLoop
		maxPause 60
		minCycles 1
		maxCycles 1
	)
)

(instance fruit11 of Extra
	(properties
		y 80
		x 190
		view rEranasPeace
		loop 3
		cycleType ExtraEndLoop
		minPause 20
		maxPause 70
		minCycles 1
		maxCycles 1
	)
)

(instance fruit12 of Extra
	(properties
		y 43
		x 148
		view rEranasPeace
		loop 1
		cycleType ExtraEndLoop
		minPause 30
		maxPause 80
		minCycles 1
		maxCycles 1
	)
)

(instance fruit13 of Extra
	(properties
		y 70
		x 128
		view rEranasPeace
		loop 2
		cycleType ExtraEndLoop
		minPause 40
		maxPause 90
		minCycles 1
		maxCycles 1
	)
)

(instance fruit14 of Extra
	(properties
		y 79
		x 145
		view rEranasPeace
		loop 1
		cycleType ExtraEndLoop
		minPause 50
		maxPause 100
		minCycles 1
		maxCycles 1
	)
)

(instance magicStone of View
	(properties
		view rEranasPeace
	)
)
