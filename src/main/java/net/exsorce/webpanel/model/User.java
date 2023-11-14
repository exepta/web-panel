package net.exsorce.webpanel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.hibernate.proxy.HibernateProxy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class User implements UserDetails
{

	@Id
	@GeneratedValue
	private Integer id;

	private String firstname;
	private String lastname;
	private String email;
	private String nickname;
	private String password;

	@Enumerated( EnumType.STRING )
	private UserRole role;

	@Override
	public Collection< ? extends GrantedAuthority > getAuthorities ()
	{
		return List.of(new SimpleGrantedAuthority( role.name() ));
	}

	@Override
	public String getUsername ()
	{
		return email;
	}

	@Override
	public boolean isAccountNonExpired ()
	{
		return true;
	}

	@Override
	public boolean isAccountNonLocked ()
	{
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired ()
	{
		return true;
	}

	@Override
	public boolean isEnabled ()
	{
		return true;
	}

	@Override
	public final boolean equals ( Object o )
	{
		if ( this == o )
			return true;
		if ( o == null )
			return false;
		Class< ? > oEffectiveClass = o instanceof HibernateProxy ?
				( ( HibernateProxy ) o ).getHibernateLazyInitializer().getPersistentClass() :
				o.getClass();
		Class< ? > thisEffectiveClass = this instanceof HibernateProxy ?
				( ( HibernateProxy ) this ).getHibernateLazyInitializer().getPersistentClass() :
				this.getClass();
		if ( thisEffectiveClass != oEffectiveClass )
			return false;
		User user = ( User ) o;
		return getId() != null && Objects.equals( getId(), user.getId() );
	}

	@Override
	public final int hashCode ()
	{
		return this instanceof HibernateProxy ?
				( ( HibernateProxy ) this ).getHibernateLazyInitializer().getPersistentClass().hashCode() :
				getClass().hashCode();
	}
}
