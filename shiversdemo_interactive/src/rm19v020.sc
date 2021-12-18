;;; Sierra Script 1.0 - (do not remove this comment)
(script# 19020)
(include sci.sh)
(use Main)
(use Procs)
(use String)
(use Motion)
(use Actor)
(use System)

(public
	rm19v020 0
)

(local
	local0
	local1
)
(instance rm19v020 of ShiversRoom
	(properties
		picture 19020
	)
	
	(method (init)
		(= local1 0)
		(efExitLeft init: 2)
		(efExitRight init: 1)
		(hsDoor init:)
		(if
		(and (proc951_11 8 19000) (== prevRoomNum 19010))
			(sounds adjChainVol: 16)
			(sounds fade: 21909 0 5 16 1 0)
			(sounds
				playChain: -1 -5 21901 -1 -1 -5 21901 -1 -1 -5 21904 -1
			)
			(sounds play: 21903 -1 48 0)
		)
		(spNarrative init: setPri: 0)
		(super init: &rest)
	)
	
	(method (newRoom n)
		(proc951_1 3)
		(cond 
			((== n 11240) (sounds fadeChain:))
			(local1 (proc951_3 43))
		)
		(sounds fade: 31950 0 15 8 1 0)
		(if (proc951_5 39) (curRoom eraseText:))
		(super newRoom: n &rest)
	)
)

(instance hsDoor of HotSpot
	(properties)
	
	(method (init)
		(self
			createPoly:
				68
				143
				72
				133
				69
				39
				77
				26
				89
				14
				107
				7
				134
				2
				160
				7
				180
				15
				191
				28
				199
				40
				197
				135
				208
				143
		)
		(super init: &rest)
	)
	
	(method (doVerb)
		(sounds stop: 11901)
		(curRoom setScript: sOpenDoor)
	)
)

(instance pDoor of Prop
	(properties
		view 19020
	)
)

(instance sOpenDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(hsDoor dispose:)
				(pDoor init: setPri: 25 1 cycleSpeed: 15 setCycle: End)
				(sounds play: 11901 0 82 self)
			)
			(1
				(efExitForward init: 3)
				(theGame handsOn:)
				(= local1 1)
				(self dispose:)
			)
		)
	)
)

(instance efExitLeft of ExitFeature
	(properties
		nextRoom 19010
	)
)

(instance efExitRight of ExitFeature
	(properties
		nextRoom 19030
	)
)

(instance efExitForward of ExitFeature
	(properties
		nextRoom 11240
	)
	
	(method (init)
		(self
			createPoly:
				68
				143
				72
				133
				69
				39
				77
				26
				89
				14
				107
				7
				134
				2
				160
				7
				180
				15
				191
				28
				199
				40
				197
				135
				208
				143
		)
		(super init: &rest)
	)
)

(instance spNarrative of ShiversProp
	(properties
		priority 25
		fixPriority 1
		view 19021
	)
	
	(method (init)
		(self createPoly: 58 60 64 60 64 65 57 65 57 60)
		(super init: &rest)
	)
	
	(method (doVerb)
		(if (== local1 0) (self setScript: sNarrative))
	)
)

(instance sNarrative of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(proc951_9 31950)
				(sounds stop: 31950)
				(spNarrative setPri: 10)
				(= cycles 1)
			)
			(1
				(theGame handsOn:)
				(if (proc951_5 39)
					(= local0
						(Str
							format:
								{ You have stepped into the world of amazing plants. For centuries, plants have_________been used to beautify man's surroundings, for food, shelter, and to heal the sick. And like Socrates, who was executed by drinking poison hemlock, man has discovered, plants have a lethal side too._}
						)
					)
					(curRoom drawText: local0 0 0)
					(local0 data: 0 dispose:)
					(sounds play: 31950 0 98 self)
				else
					(sounds play: 31950 0 98 self)
				)
			)
			(2
				(if (proc951_5 39) (curRoom eraseText:))
				(spNarrative setPri: 0)
				(self dispose:)
			)
		)
	)
)
