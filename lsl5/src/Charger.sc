;;; Sierra Script 1.0 - (do not remove this comment)
(script# CHARGER)
(include game.sh)
(use Main)
(use Sound)
(use Motion)
(use Invent)
(use Actor)
(use System)

(public
	sPlugCharger 0
	sUnplugCharger 1
	sGetShocked 2
	charger 3
)

(local
	chargerPosn = [160 64 108 260 19 146]
)
(instance sPlugCharger of Script
	
	(method (changeState newState &tmp i)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 161
					setLoop: 0
					setCel: 0
					cycleSpeed: 10
					ignoreActors: 1
					illegalBits: 0
					setCycle: EndLoop self
				)
			)
			(1
				(ego setLoop: 1 setCel: 0 setCycle: EndLoop self)
			)
			(2
				(= i 0)
				(while (!= [chargerPosn i] 0)
					(if (== [chargerPosn i] curRoomNum)
						(charger
							x: [chargerPosn (++ i)]
							y: [chargerPosn (++ i)]
							init:
						)
						(break)
					)
					(= i (+ i 3))
				)
				(ego setLoop: 0 setCel: 255 setCycle: BegLoop self)
			)
			(3
				(ego put: iCharger curRoom setLoop: 1 normalize:)
				(SolvePuzzle 8 fPlugCharger)
				(TimePrint 22 0)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sPlugCamcorder of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 161
					setLoop: 0
					setCel: 0
					cycleSpeed: 10
					ignoreActors: 1
					illegalBits: 0
					setCycle: EndLoop self
				)
			)
			(1
				(ego setLoop: 2 setCel: 0 setCycle: EndLoop self)
			)
			(2
				(charger setLoop: 4)
				(ego setLoop: 0 setCel: 255 setCycle: BegLoop self)
			)
			(3
				(ego put: iCamcorder (Inventory at: iCharger) setLoop: 1 normalize:)
				(charger approachVerbs: verbDo verbUse verbLook)
				(SolvePuzzle 3 fPlugCamcorder)
				(TimePrint 22 1)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sUnplugCharger of Script
	
	(method (changeState newState &tmp power)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 161
					setLoop: 0
					setCel: 0
					cycleSpeed: 10
					ignoreActors: 1
					illegalBits: 0
					setCycle: EndLoop self
				)
			)
			(1
				(charger dispose:)
				(ego setLoop: 1 setCel: 255 setCycle: BegLoop self)
			)
			(2
				(ego setLoop: 0 setCel: 255 setCycle: BegLoop self)
			)
			(3
				(ego get: iCharger get: iCamcorder setLoop: 1 normalize:)
				((Inventory at: iCamcorder) owner: 23)
				(charger setLoop: 3 approachVerbs: verbDo verbUse)
				(SolvePuzzle 1 fUnplugCharger)
				(if (== (charger loop?) 4)
					(TimePrint 22 2)
				else
					(TimePrint 22 3)
				)
				(HandsOn)
				(= power (* 300 (/ batteryStrength 20)))
				(cameraTimer setReal: cameraTimer (/ power 100))
				(self dispose:)
			)
		)
	)
)

(instance sGetShocked of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					posn: (ego x?) (- (ego y?) 2)
					view: 560
					setLoop: 0
					setCel: 0
					cycleSpeed: 10
					setCycle: EndLoop self
				)
			)
			(1
				(shockSound number: 561 setLoop: 1 play:)
				(ego setPri: 13 setLoop: 1 setCycle: Forward)
				(= cycles (Random 25 40))
			)
			(2
				(shockSound number: 147 play:)
				(ego setLoop: 2 setCel: 0 setCycle: EndLoop self)
			)
			(3
				(ego setLoop: 3 setCel: 0 setCycle: EndLoop self)
			)
			(4
				(ego setLoop: 2 normalize:)
				(= ticks 60)
			)
			(5
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance shockSound of Sound
	(properties
		number 561
	)
)

(instance charger of View
	(properties
		description {the battery charger}
		view 161
		loop 3
	)
	
	(method (init)
		(super init:)
		(self approachVerbs: verbDo verbUse)
		(= approachX (+ x 12))
		(= approachY (+ y 1))
	)
	
	(method (doVerb theVerb theItem &tmp [str 50])
		(switch theVerb
			(verbLook
				(if (== ((Inventory at: iCamcorder) owner?) (Inventory at: iCharger))
					(Format @str 22 4 (/ batteryStrength 20) 37)
					(TimePrint @str 30 1
						#title {Camcorder Battery Strength}
					)
				else
					(TimePrint 22 5)
				)
			)
			(verbDo
				(curRoom setScript: sUnplugCharger)
			)
			(verbUse
				(switch theItem
					(iCamcorder
						(curRoom setScript: sPlugCamcorder)
					)
					(else 
						(super doVerb: theVerb theItem &rest)
					)
				)
			)
			(verbZipper
				(TimePrint 22 6)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)
