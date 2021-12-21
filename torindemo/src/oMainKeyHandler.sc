;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64010)
(include sci.sh)
(use Main)
(use soBooglePouch)
(use Events)
(use Print)
(use System)

(public
	oMainKeyHandler 0
	oRightClickHandler 1
)

(local
	local0
	[local1 8] = [3 6 0 4 2 5 1 7]
)
(instance oMainKeyHandler of EventCode
	(properties)
	
	(method (handleEvent event &tmp theGameOCantBeHereHandler)
		(if (not (& (event type?) $000c))
			(Prints {oMainKeyHandler})
			(return)
		)
		(if (not (& (event type?) evKEYBOARD)) (return))
		(event claimed: 1)
		(switch (event message?)
			(KEY_EXECUTE
				(= theGameOCantBeHereHandler
					(theGame oCantBeHereHandler?)
				)
				(= theGameOCantBeHereHandler
					(Max 1 (-- theGameOCantBeHereHandler))
				)
				(theGame oCantBeHereHandler: theGameOCantBeHereHandler)
				(ego setSpeed: theGameOCantBeHereHandler)
			)
			(KEY_SUBTRACT
				(= theGameOCantBeHereHandler
					(theGame oCantBeHereHandler?)
				)
				(= theGameOCantBeHereHandler
					(Min 10 (++ theGameOCantBeHereHandler))
				)
				(theGame oCantBeHereHandler: theGameOCantBeHereHandler)
				(ego setSpeed: theGameOCantBeHereHandler)
			)
			(61
				(= theGameOCantBeHereHandler 6)
				(ego setSpeed: theGameOCantBeHereHandler)
				(theGame oCantBeHereHandler: theGameOCantBeHereHandler)
			)
			(KEY_SHIFT
				(if (= local0 (not local0))
					(PalCycle 0 136 235 1 10)
				else
					(PalCycle 4)
				)
			)
			(KEY_NUMPAD4
				(if (& (event modifiers?) emSHIFT)
					(= mouseX (- mouseX 7))
				else
					(= mouseX (- mouseX 30))
				)
				(event x: mouseX y: mouseY type: 2 claimed: 0)
				(theCursor posn: mouseX mouseY)
			)
			(KEY_RIGHT
				(if (& (event modifiers?) emSHIFT)
					(= mouseX (+ mouseX 7))
				else
					(= mouseX (+ mouseX 30))
				)
				(event x: mouseX y: mouseY type: 2 claimed: 0)
				(theCursor posn: mouseX mouseY)
			)
			(KEY_UP
				(if (& (event modifiers?) emSHIFT)
					(= mouseY (- mouseY 7))
				else
					(= mouseY (- mouseY 30))
				)
				(event x: mouseX y: mouseY type: 2 claimed: 0)
				(theCursor posn: mouseX mouseY)
			)
			(KEY_NUMPAD2
				(if (& (event modifiers?) emSHIFT)
					(= mouseY (+ mouseY 7))
				else
					(= mouseY (+ mouseY 30))
				)
				(event x: mouseX y: mouseY type: 2 claimed: 0)
				(theCursor posn: mouseX mouseY)
			)
			(KEY_SPACE
				(event type: 1 claimed: 0)
			)
			(KEY_RETURN
				(event type: 1 claimed: 0)
			)
			(else  (event claimed: 0))
		)
		(event claimed?)
	)
)

(instance oRightClickHandler of EventCode
	(properties)
	
	(method (handleEvent event &tmp temp0)
		(if
			(and
				(& (event type?) evMOUSEBUTTON)
				(& (event modifiers?) emSHIFT)
				(user canControl:)
				(ego oCast?)
				(ego isNotHidden:)
				(ego plane?)
				((ego plane?) nScreenOrgY:)
				((ego plane?) onMe: (event globalize: yourself:))
			)
			(event localize: (ego plane?))
			(= temp0 (proc64018_3 ego (event x?) (event y?)))
			(ego setMotion: 0)
			(ego
				setLoop:
					[local1 (/
						(umod
							(+
								(GetAngle
									(ego x?)
									(ego y?)
									(temp0 at: 0)
									(temp0 at: 1)
								)
								22
							)
							360
						)
						45
					)]
			)
			(ego posn: (temp0 at: 0) (temp0 at: 1))
			(temp0 dispose:)
			(event claimed: 1)
		)
		(event claimed?)
	)
)
