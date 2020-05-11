;;; Sierra Script 1.0 - (do not remove this comment)
(script# OGRE_HOUSE)
(include game.sh)
(use Main)
(use Intrface)
(use Game)
(use System)

(public
	regOgreHouse 0
)
(synonyms
	(giant troll man)
	(giantess ass giantess woman)
)

(instance regOgreHouse of Region
	(properties)
	
	(method (init)
		(if initialized (return))
		(= keep TRUE)
		(super init:)
		(if (not ogreCameHome) (self setScript: ogreHere))
		(= noWearCrown TRUE)
	)
	
	(method (doit)
		(super doit:)
	)
	
	(method (dispose)
		(if (== keep FALSE) (= noWearCrown FALSE) (super dispose:))
	)
	
	(method (newRoom newRoomNumber)
		(if
			(and
				(== newRoomNumber 49)
				(== curRoomNum 48)
				(== (ogreHere state?) 0)
			)
			(ogreHere seconds: 90)
		)
		(if (and (== newRoomNumber 51) (ego has: iMagicHen))
			(ogreHere changeState: 3)
		)
	)
)

(instance ogreHere of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 50))
			(1
				(if (and (!= curRoomNum 51) (!= curRoomNum 48))
					(Print 602 0 #time 5)
				)
				(if (== curRoomNum 48)
					(= seconds 60)
				else
					(= seconds 10)
				)
			)
			(2
				(= ogreState ogreAWAY)
				(if (== curRoomNum 51) (Print 602 1) (= seconds 60))
			)
			(3
				(if (not ogreCameHome)
					(= ogreState ogreEATING)
					(if (== curRoomNum 51) (Print 602 2))
				)
				(= seconds 60)
			)
			(4
				(if (and (== curRoomNum 51) (not ogreCameHome))
					(= ogreState ogreLEAVING)
					(Print 602 3)
				)
			)
		)
	)
)
