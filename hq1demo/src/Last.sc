;;; Sierra Script 1.0 - (do not remove this comment)
(script# LAST)
(include game.sh)
(use Main)
(use Intrface)
(use LoadMany)
(use Reverse)
(use Sound)
(use Save)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Endo 0
)

(instance Endo of Room
	(properties
		picture rBigEgo
		style IRISIN
	)
	
	(method (init)
		(LoadMany SOUND sWind sEndGame sEndGameIBM)
		(super init:)
		(wind init: play:)
		(self setScript: openMusic)
		(addToPics
			add: soYou want toBeA chump
			eachElementDo: #init
			doit:
		)
		(hero init:)
		(shine init:)
		(shine2 init:)
		(hairL init: setCycle: Forward)
		(hairT init: setCycle: Reverse)
		(hairR init: setCycle: Reverse)
		(cape init: setCycle: Forward)
		(RColWink init:)
		(LColWink init:)
		(= endAnimationTimer 1)
		(Print 12 0
			#width 120
			#at 8 140
			#dispose
			#window aWin
		)
	)
	
	(method (doit)
		(switch (-- endAnimationTimer)
			(55
				(shine2 setPri: 15 setCycle: CycleTo 3 1)
			)
			(35
				(RColWink setPri: 15 setCycle: CycleTo 3 1)
			)
			(15
				(shine setPri: 15 setCycle: CycleTo 3 1)
			)
			(1
				(LColWink setPri: 15 setCycle: CycleTo 3 1)
				(= endAnimationTimer 75)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(cls)
		(super dispose:)
		(DisposeScript REVERSE)
	)
)

(instance hero of View
	(properties
		y 189
		x 167
		view rBigEgo
		priority 8
	)
)

(instance shine of Prop
	(properties
		y 75
		x 148
		view rBigEgo
		loop 1
		cel 3
		priority 15
	)
)

(instance shine2 of Prop
	(properties
		y 141
		x 140
		view rBigEgo
		loop 1
		cel 3
		priority 15
	)
)

(instance hairL of Prop
	(properties
		y 82
		x 138
		view rBigEgo
		loop 2
		priority 15
		signal fixPriOn
	)
)

(instance hairT of Prop
	(properties
		y 67
		x 150
		view rBigEgo
		loop 3
		priority 15
		signal fixPriOn
	)
)

(instance hairR of Prop
	(properties
		y 92
		x 178
		view rBigEgo
		loop 4
		priority 15
		signal fixPriOn
	)
)

(instance cape of Prop
	(properties
		y 141
		x 206
		view rBigEgo
		loop 5
		priority 5
		cycleSpeed 2
	)
)

(instance soYou of PicView
	(properties
		y 30
		x 48
		view vSubtitle
		loop 1
		priority 12
	)
)

(instance want of PicView
	(properties
		y 30
		x 127
		view vSubtitle
		loop 1
		cel 1
		priority 12
	)
)

(instance toBeA of PicView
	(properties
		y 30
		x 207
		view vSubtitle
		loop 1
		cel 2
		priority 12
	)
)

(instance chump of PicView
	(properties
		y 30
		x 286
		view vSubtitle
		loop 1
		cel 3
		priority 12
	)
)

(instance RColWink of Prop
	(properties
		y 79
		x 278
		view rBigEgo
		loop 1
		cel 3
	)
)

(instance LColWink of Prop
	(properties
		y 79
		x 39
		view rBigEgo
		loop 1
		cel 3
	)
)

(instance wind of Sound
	(properties
		number sWind
		priority 1
		loop -1
	)
)

(instance heroMusic of Sound
	(properties
		number sEndGameIBM
		priority 2
	)
)

(instance aWin of SysWindow
	(properties
		back vLMAGENTA
	)
)

(instance openMusic of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(heroMusic
					init:
					number: (if (== numVoices 1) sEndGameIBM else sEndGame)
					loop: 1
					play:
				)
				(= seconds 9)
			)
			(1
				(heroMusic fade:)
				(= seconds 10)
			)
			(2
				(theGame restart:)
			)
		)
	)
)
