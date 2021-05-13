;;; Sierra Script 1.0 - (do not remove this comment)
(script# LBDOOR)
(include game.sh) (include "16.shm")
(use Main)
(use Door)
(use Sound)
(use Motion)


(class LbDoor of Door

	(method (init)
		(super init:)
		(self approachVerbs: openVerb listenVerb V_SKELETON_KEY)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_SKELETON_KEY
				(if cel
					(messager say: N_DOOR V_SKELETON_KEY C_ALREADY_OPEN 0 0 LBDOOR)
					1
				else
					(messager say: N_DOOR V_SKELETON_KEY C_WONT_OPEN 0 0 LBDOOR)
					1
				)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
	
	(method (open)
		(if locked
			(if (!= msgType CD_MSG)
				(doorSound number: 48 play:)
			)
			(messager say: N_DOOR NULL C_LOCKED 0 0 LBDOOR)
			1
		else
			(if (user controls?) (theGame handsOff:))
			(= doorState doorOpening)
			(self setCycle: EndLoop self)
			(if openSnd (doorSound number: openSnd play:))
			(if doubleDoor (doubleDoor setCycle: EndLoop))
		)
	)
	
	(method (listen)
		(messager say: N_DOOR NULL C_HEAR_NOTHING 0 0 16)
		(return TRUE)
	)
)

(instance doorSound of Sound
	(properties
		flags mNOPAUSE
	)
)
