;;; Sierra Script 1.0 - (do not remove this comment)
(script# 12)
(include game.sh) (include "12.shm")
(use Main)
(use Procs)
(use PQRoom)
(use DSelector)
(use String)
(use Actor)
(use System)

(public
	showMikeTalking 0
)

(procedure (PrintAsk n v c m theText &tmp str)
	(if (not (Message MsgGet m n v c 1))
		(return)
	)
	(= str (String new: 400))
	(Message MsgGet m n v c 1 (str data?))
	(theText copy: (str data?))
	(while (Message MsgNext (str data?))
		(theText cat: (str data?))
	)
	(str dispose:)
)

(instance showMikeTalking of PQRoom
	(properties
		picture 12
	)
	
	(method (init &tmp str)
		(Load RES_PIC 12)
		(super init: &rest)
		(= str (String new: 50))
		(PrintAsk N_ASKWHAT NULL C_KNOW_TACTICS 12 str)
		(ourSelector setText: str)
		(PrintAsk N_ASKWHAT NULL C_WHO_TACTICS 12 str)
		(ourSelector setText: str)
		(PrintAsk N_ASKWHAT NULL C_WHEN_IS_SWAT_CALLED 12 str)
		(ourSelector setText: str)
		(PrintAsk N_ASKWHAT NULL C_WHAT_DO_NO_CRISIS 12 str)
		(ourSelector setText: str)
		(PrintAsk N_ASKWHAT NULL C_TRAINING_TIME 12 str)
		(ourSelector setText: str)
		(PrintAsk N_ASKWHAT NULL C_QUALIFY_SWAT 12 str)
		(ourSelector setText: str)
		(PrintAsk N_ASKWHAT NULL C_D_PLATOON 12 str)
		(ourSelector setText: str)
		(PrintAsk N_ASKWHAT NULL C_HOW_LONG_BEEN_IN_SWAT 12 str)
		(ourSelector setText: str)
		(PrintAsk N_ASKWHAT NULL C_SWAT_RECOMMEND 12 str)
		(ourSelector setText: str)
		(PrintAsk N_ASKWHAT NULL C_WEAPONS 12 str)
		(ourSelector setText: str)
		(PrintAsk N_ASKWHAT NULL C_ASSAULTER 12 str)
		(ourSelector setText: str)
		(PrintAsk N_ASKWHAT NULL C_TRAIN_223_308 12 str)
		(ourSelector setText: str)
		(str dispose:)
		(ourSelector setSize: init: updatePlane: current: TRUE draw:)
		(namePlate init:)
		(self setScript: demoScript)
	)
)

(instance demoScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 1))
			(1
				(tempActor init: hide:)
				(PlayRobot 12 35 28 tempActor)
			)
			(2
				(theMusic fade: 0 15 10 1 self)
			)
			(3
				(tempActor dispose:)
				(curRoom newRoom: 13)
			)
		)
	)
)

(instance tempActor of Actor
	(properties
		view 10
	)
)

(instance namePlate of View
	(properties
		x 29
		y 93
		view 120
	)
)

(instance ourSelector of DSelector
	(properties
		x 153
		y 32
		font 999
		length 16
		width 117
		fore 28
		back 0
	)
	
	(method (init)
		(= upButton (= downButton -1))
		(= textHeight (+ textHeight 2))
		(self moveTo: x y font: smallFont)
		(super init: cast &rest)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(super dispose: &rest)
	)
	
	(method (onMe)
		(return 0)
	)
)
