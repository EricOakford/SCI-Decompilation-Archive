;;; Sierra Script 1.0 - (do not remove this comment)
(script# 298)
(include game.sh)
(use Main)
(use Intrface)
(use Game)
(use User)
(use Actor)
(use System)

(public
	debugRm 0
)

(instance debugRm of Locale
	(properties)
	
	(method (init)
		(super init:)
		(mouseDownHandler add: self)
		(keyDownHandler add: self)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp evt obj nX nY i whichSkill [str 40])
		(if
		(or (not debugging) (event claimed?))
			(return)
		)
		(switch (event type?)
			(mouseDown
				(if (& (event modifiers?) shiftDown)
					(= obj
						(Print
							(Format @str 298 0 (event x?) (event y?))
							#at 150 100
							#font 999
							#dispose
						)
					)
					(while (!= mouseUp ((= evt (Event new:)) type?))
						(evt dispose:)
					)
					(obj dispose:)
					(evt dispose:)
				)
			)
			(keyDown
				(event claimed: TRUE)
				(switch (event message?)
					(`?
						(Print 298 1)
						; Key commands:
						; ALT-S Show cast
						; ALT-M Show Memory
						; ALT-T Teleport
						; ALT-V Visual
						; ALT-P Priority
						; ALT-C Control
						; ALT-I Get InvItem
						; ALT-B Set Ego's Bucks
						; ALT-K Set one of Ego's sKills
						; ALT-X Make Ego eXtra special
					)
					(`@s
						(= i (cast first:))
						(while i
							(= obj (NodeValue i))
							(Print
								(Format @str 298 2
									(obj view?)
									(obj x?)
									(obj y?)
									(if (& (obj signal?) notUpd) {stopUpd:\0A} else {})
									(if (& (obj signal?) ignrAct)
										{ignoreActors:\0A}
									else
										{}
									)
									(if
										(or
											(== (obj superClass?) Actor)
											(== (obj superClass?) Ego)
										)
										(obj illegalBits?)
									else
										-1
									)
								)
								#title (obj name?)
								#icon (obj view?) (obj loop?) (obj cel?)
							)
							(= i (cast next: i))
						)
					)
					(`@e
						(Format @str 298 3
							(ego x?)
							(ego y?)
							(ego loop?)
							(ego cel?)
						)
						(Print @str #icon (ego view?) 6 0 7 0)
					)
					(`@i
						(= i (GetNumber {ID number of the object?}))
						(ego get: i)
					)
					(`@t
						(curRoom newRoom: (GetNumber {Which room number?}))
					)
					(`@v (Show VMAP))
					(`@p (Show PMAP))
					(`@c (Show CMAP))
					(`@m (theGame showMem:))
					(`@b
						(Print (Format @str 298 4 [invNum iSilver]))
						;Our Hero has %d silvers.
						(= [invNum iSilver] (GetNumber {Enter Silvers.}))
					)
					(`@k
						(= whichSkill (GetNumber {Change which Stat/Skill?}))
						(= [egoStats whichSkill]
							(GetNumber {Enter new value:} [egoStats whichSkill])
						)
					)
					(`@x
						(= whichSkill 0)
						(while (< whichSkill 25)
							(= [egoStats whichSkill] 80)
							(++ whichSkill)
						)
						(= [egoStats EXPER] 1900)
						(= [egoStats HEALTH] (MaxHealth))
						(= [egoStats STAMINA] (MaxStamina))
						(= [egoStats MANA] (MaxMana))
						(Print 298 5)
						; Why, you feel better already!
					)
					;CI: NOTE: I've added this additional debug entry (alt-y) for whatever I want to display.
					;in this case, I was trying to find out what's going on in skillTicks.
					;(`@y	
					;	(Print (Format @str "skillTick Vitality: %d" 
					;			[egoStats (= [skillTicks VIT]
					;			(+ [skillTicks VIT] 10)
					;			)]
					;			))
					;)
					(else  (event claimed: FALSE))
				)
			)
		)
	)
)
