;;; Sierra Script 1.0 - (do not remove this comment)
(script# 767)
(include sci.sh)
(use Main)
(use Motion)
(use Actor)


(class Door of Prop
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 0
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		description 0
		sightAngle 26505
		actions 0
		onMeCheck $6789
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 26505
		lookStr 0
		yStep 2
		view 0
		loop 0
		cel 0
		priority 0
		underBits 0
		signal $0000
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		palette 0
		cycleSpeed 0
		script 0
		cycler 0
		timer 0
		detailLevel 0
		openDoorNumber 8122
		closeDoorNumber 8124
	)
	
	(method (setCycle cType)
		(if (and argc (IsObject cType))
			(theAudio
				number:
					(if (cType isKindOf: Beg)
						(self closeDoorNumber?)
					else
						(self openDoorNumber?)
					)
				loop: 1
				play:
			)
		)
		(super setCycle: cType &rest)
	)
)
