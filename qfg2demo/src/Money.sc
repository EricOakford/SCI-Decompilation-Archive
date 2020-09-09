;;; Sierra Script 1.0 - (do not remove this comment)
(script# MONEY) ;5
(include game.sh)
(use Main)
(use Flame)
(use Intrface)
(use LoadMany)
(use StopWalk)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Money 0
)

(instance Money of Room
	(properties
		picture rMoneychanger
		style IRISIN
	)
	
	(method (init)
		(LoadMany VIEW rMoneychanger vMoneychanger vMoneychangerGuard)
		(super init:)
		(ego
			posn: -10 205
			illegalBits: 0
			ignoreActors:
			setPri: -1
			setCycle: StopWalk 4
			init:
		)
		(sconce1 init:)
		(flame1 init: setCycle: Forward)
		(sconce2 init:)
		(flame2 init: setCycle: Forward)
		(changer init:)
		(guard init:)
		(addToPics doit:)
		(Print MONEY 0
			#at -1 12
			#dispose
		)
		(self setScript: rmScript)
	)
)

(instance changer of Prop
	(properties
		x 182
		y 87
		view vMoneychanger
		priority 13
		signal fixPriOn
	)
)

(instance guard of PicView
	(properties
		x 83
		y 110
		view vMoneychangerGuard
	)
)

(instance sconce1 of PicView
	(properties
		x 140
		y 47
		view rMoneychanger
	)
)

(instance sconce2 of PicView
	(properties
		x 234
		y 59
		view rMoneychanger
	)
)

(instance flame1 of Flame
	(properties
		x 140
		y 41
		view rMoneychanger
		loop 1
		priority 13
		signal fixPriOn
	)
)

(instance flame2 of Flame
	(properties
		x 234
		y 53
		view rMoneychanger
		loop 1
		priority 13
		signal fixPriOn
	)
)

(instance rmScript of Script
	
	(method (dispose)
		(LoadMany SOUND rHaremGirlHouse sCaravan)
		(Load SCRIPT REVERSE)
		(Load SCRIPT HAREM)
		(Load PICTURE rHaremGirlHouse)
		(LoadMany VIEW rHaremGirlHouse vHaremGirl vServant vEgoPeek)
		(Load SCRIPT DESERT)
		(Load PICTURE rGenDesert)
		(Load VIEW vEgoRidingSaurus)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 164 113 self)
			)
			(1
				(changer setCycle: EndLoop self)
			)
			(2
				(cls)
				(client newRoom: HAREM)
				(self dispose:)
			)
		)
	)
)
