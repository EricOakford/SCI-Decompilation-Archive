;;; Sierra Script 1.0 - (do not remove this comment)
(script# DWARF_HOUSE)
(include game.sh)
(use Main)
(use Sound)
(use Game)

(public
	regDwarfHouse 0
)
(synonyms
	(dwarf ass man person)
)

(instance theSound of Sound
	(properties
		number 62
		loop -1
	)
)

(instance regDwarfHouse of Region
	(method (init)
		(if initialized (return))
		(super init:)
		(Load VIEW 290)
		(Load SOUND 62)
		(= noWearCrown TRUE)
		(= keep TRUE)
		(theSound owner: self init:)
	)
	
	(method (dispose)
		(if (not keep)
			(= noWearCrown FALSE)
			(super dispose:)
		)
	)
	
	(method (notify param1)
		(if param1
			(theSound play:)
		else
			(theSound client: 0 stop:)
		)
	)
)
