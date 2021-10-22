;;; Sierra Script 1.0 - (do not remove this comment)
(script# DEBUG)
(include game.sh)
(use Main)
(use Intrface)
(use File)
(use Game)
(use User)
(use Menu)
(use System)

(public
	rm20 0
)

(instance rm20 of Locale
	(method (handleEvent event &tmp n i obj evt [str1 30] [str2 30] [str3 30])
		(if (or (not debugging) (event claimed?)) (return))
		(switch (event type?)
			(mouseDown
				(cond 
					((& (event modifiers?) shiftDown)
						(event claimed: TRUE)
						(= obj
							(Print
								(Format @str1 20 0 (event x?) (event y?))
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
					((& (event modifiers?) ctrlDown)
						(event claimed: TRUE)
						(User canControl: TRUE)
						(while (!= mouseUp ((= evt (Event new:)) type?))
							(GlobalToLocal evt)
							(ego posn: (evt x?) (evt y?) setMotion: 0)
							(Animate (cast elements?) FALSE)
							(evt dispose:)
						)
						(evt dispose:)
					)
				)
			)
			(keyDown
				(switch (event message?)
					(`@c
						(Show CMAP)
						(Animate (cast elements?) FALSE)
						(while (== nullEvt ((= event (Event new:)) type?))
							(event dispose:)
						)
						(event dispose:)
						(Show VMAP)
						(return)
					)
					(`@d
						(SetDebug)
					)
					(`@e
						(if (Load VIEW (= n (GetNumber {New Ego View:})))
							(= currentEgoView n)
							(NormalEgo)
						)
					)
					(`@f
						(^= debugOn TRUE)
						(Print
							(Format @str1 20 1 (if debugOn {_} else { NOT_}))
						)
					)
					(`@g
						(if (Load PICTURE 999)
							(DrawPic 999 VSHUTTER TRUE)
						else
							(SetDebug)
						)
					)
					(`@i
						(User canInput: TRUE)
					)
					(`@m
						(theGame showMem:)
					)
					(`@p
						(Show PMAP)
					)
					(`@r
						(Print (Format @str1 20 2 curRoomNum))
					)
					(`@s
						(if playingAsPatti
							(= playingAsPatti FALSE)
							(= currentEgoView 700)
							(= currentEgo (Format @egoName 20 3))
						else
							(= playingAsPatti TRUE)
							(= currentEgoView 800)
							(= currentEgo (Format @egoName 20 4))
						)
						(NormalEgo)
					)
					(`@t
						(Print
							(Format @str1 20 5
								(/ orchidSeconds 600)
								(/ (mod orchidSeconds 600) 10)
							)
						)
					)
					(`@v
						(Show VMAP)
					)
					(`@w
						(= str1 0)
						(if
						(!= -1 (GetInput @str1 50 {Writing to "ego.log"}))
							(Format @str2 20 6 curRoomNum)
							(Format @str3 20 7
								(ego view?)
								(ego loop?)
								(ego cel?)
								(ego x?)
								(ego y?)
								(ego priority?)
							)
							(File
								name: {ego.log}
								write: @str2 @str1 {]_} @str3 {\0D\n}
								close:
							)
						)
					)
					(`@x
						(= quit TRUE)
					)
					(`@z
						(= quit TRUE)
					)
					;the next few use the Ctrl modifier
					(`^t
						(= n (GetNumber {Teleport to}))
						(if (Load SCRIPT n)
							(NormalEgo)
							(curRoom newRoom: n)
						else
							(Print 20 8)
							(SetDebug)
						)
					)
					(`^d
						(if programControl
							(= programControl FALSE)
							(TheMenuBar draw:)
							(StatusLine enable:)
							(NormalEgo)
						else
							(= programControl TRUE)
							(= n (GetNumber {Teleport to}))
							(if (Load SCRIPT n)
								(curRoom newRoom: n)
							else
								(SetDebug)
							)
						)
					)
					(`^e
						(Print
							(Format
								@str1
								{view %d loop %d cel %d posn %d %d pri %d OnControl $%x Origin on $%x}
								(ego view?)
								(ego loop?)
								(ego cel?)
								(ego x?)
								(ego y?)
								(ego priority?)
								(ego onControl:)
								(ego onControl: origin)
							)
							#icon (ego view?) (ego loop?) (ego cel?)
						)
					)
					(BACKSPACE
						(theGame showMem:)
					)
				)
			)
			(saidEvent
				(if (Said 'tp')
					(= n (GetNumber {Teleport to}))
					(if (Load SCRIPT n)
						(NormalEgo)
						(curRoom newRoom: n)
					else
						(Print 20 8)
						(SetDebug)
					)
				)
				(if
					(and
						(Said 'pitch>')
						(= i (inventory saidMe:))
					)
					(event claimed: TRUE)
					(if (not (i ownedBy: ego))
						(Print 20 9)
					else
						(Print 20 10)
						(i moveTo: -1)
					)
				)
				(if
					(and
						(Said 'get>')
						(= i (inventory saidMe:))
					)
					(event claimed: TRUE)
					(if (i ownedBy: ego)
						(Print 20 11)
					else
						(Print 20 12)
						(i moveTo: ego)
					)
				)
			)
		)
		(if (not (event claimed?))
			(super handleEvent: event)
		)
	)
)
